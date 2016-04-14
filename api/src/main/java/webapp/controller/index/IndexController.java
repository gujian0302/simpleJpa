package webapp.controller.index;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gj on 16/4/10.
 */
@Controller
@RequestMapping(value="/")
public class IndexController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="",method = RequestMethod.GET)
    public String index(){

        return "index";
    }
}
