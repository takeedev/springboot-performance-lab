package takee.dev.performance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/slow")
    public String slow() throws InterruptedException {
        Thread.sleep(200);
        return "ok";
    }

}
