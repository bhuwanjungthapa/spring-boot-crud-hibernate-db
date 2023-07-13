package WebUiCrud.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    //create a mapping for "/helloworld"
    @GetMapping("/hello")
    public String sayHello(Model themodel)
    {
        themodel.addAttribute("thedate",new java.util.Date());
        return "helloworld";
    }
}
