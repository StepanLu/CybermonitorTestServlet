package by.cybermonitor.test.service;

import by.cybermonitor.test.model.S2551Fields;
import com.datastax.driver.core.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by StepLuch on 29.06.15.
 */
public class CassandraService {

    private Cluster cluster;
    private String db_url = "89.208.152.54";
    private String db_user = "cassandra";
    private String db_password = "cassandra";

    public CassandraService() {
        cluster = Cluster.builder().withCredentials(db_user, db_password).addContactPoint(db_url).build();
        Metadata metadata = cluster.getMetadata();
        cluster.getConfiguration().getSocketOptions().setConnectTimeoutMillis(30000);
        cluster.getConfiguration().getSocketOptions().setReadTimeoutMillis(30000);
        cluster.getConfiguration().getSocketOptions().setKeepAlive(true);
        System.err.println("Connected to cluster: " + metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            System.err.println("Datacenter: " + host.getDatacenter() + "; Host: " + host.getAddress() + "; Rack: " + host.getRack());
        }
    }

    public JSONArray getDataList(int obj_id, long from, long to){
        JSONArray list = new JSONArray();
        Session session= cluster.connect("devices");
        try {
            PreparedStatement statement = session.prepare("SELECT record_index, event_code, lat, lng, speed, course, last_drive, gps_valid, event_time FROM device_2_3_events WHERE object_id = ? AND event_time >= ? AND event_time <= ?;");
            BoundStatement boundStatement = new BoundStatement(statement);
            boundStatement.setInt(1, obj_id);
            boundStatement.setLong(2, from);
            boundStatement.setLong(3, to);
            ResultSetFuture rset = session.executeAsync(boundStatement);
            ResultSet result = null;
            if (rset.getUninterruptibly() != null) {
                result = rset.getUninterruptibly();
            }
            constructFiels(result, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void constructFiels(ResultSet result, JSONArray list){
        for (Row row : result.all()) {
            try {
                JSONObject jo = new JSONObject();
                jo.put("id", row.getInt("record_index"));
                jo.put("time", row.getLong("event_time"));
                jo.put("lat", row.getDouble("lat"));
                jo.put("lng", row.getDouble("lng"));
                jo.put("speed", row.getInt("speed"));
                jo.put("course", row.getInt("course"));
                jo.put("last_interval", row.getDouble("last_drive"));
                jo.put("gps_valid", row.getBool("gps_valid"));
                jo.put("code", row.getInt("event_code"));
                list.put(jo);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
