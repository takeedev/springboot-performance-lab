package takee.dev.performance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/slow")
    public String slow() throws InterruptedException {
        var strList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            strList.add("test");
        }
        Thread.sleep(200);
        return "ok";
    }

}
