package webapp.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by gj on 16/4/13.
 */
@RestController
@RequestMapping(value="/admin")
public class AdminController {


    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(){

        return "login";
    }

}
