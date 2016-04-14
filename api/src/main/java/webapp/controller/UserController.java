package webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webapp.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gj on 16/4/1.
 */
@RestController
@RequestMapping(value="/user")
public class UserController {


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Map<String,Object> list(){


        Integer i = 2;

        HashMap<String,Object> result = new HashMap<String, Object>();
        result.put("1",i);


        return result;
    }
}

