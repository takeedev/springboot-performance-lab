package takee.dev.performance.controller;

import static java.lang.Math.sqrt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

  @GetMapping("/slow")
  public String slow() {
    for (int i = 0; i < 1000; i++) {
    Thread.startVirtualThread(
          () -> {
            while (true) {
              sqrt(Math.random());
            }
          });
    }
    return "ok";
  }
}