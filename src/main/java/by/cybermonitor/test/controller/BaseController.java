package by.cybermonitor.test.controller;

import by.cybermonitor.test.service.CassandraService;
import org.json.JSONArray;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class BaseController {

    private static int counter = 0;
    private static final String VIEW_INDEX = "index";
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        model.addAttribute("message", "Welcome");
        model.addAttribute("counter", ++counter);
        logger.debug("[welcome] counter : {}", counter);

        // Spring uses InternalResourceViewResolver and return back index.jsp
        return VIEW_INDEX;

    }

    @RequestMapping(value = "/getHistory", method = RequestMethod.POST)
    public String getHistory(@RequestParam(value="object_id", required=false) int objectId,
                             @RequestParam(value="from", required=false) long from,
                             @RequestParam(value="to", required=false) long to,
            ModelMap model) {

        CassandraService service = new CassandraService();
        JSONArray array = service.getDataList(objectId, from, to);
        model.addAttribute("time_start_transfer", System.currentTimeMillis());
        model.addAttribute("track", array);
        model.addAttribute("obj_id", objectId);
        return "history";

    }
}