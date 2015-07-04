package by.cybermonitor.test.service;

import org.json.JSONArray;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by StepLuch on 30.06.15.
 */
public class CassandraServiceTest {

    @Test
    public void getDataListTest(){
        CassandraService service = new CassandraService();
        JSONArray array = service.getDataList(151, 1435049511528L, 1435649511528L);
        System.err.println("Array length: " + array.length());
        assertTrue(array.length() > 0);
}

}