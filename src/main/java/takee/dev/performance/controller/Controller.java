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
      int cores = Runtime.getRuntime().availableProcessors();
      for (int i = 0; i < cores; i++) {
          new Thread(() -> {
              long x = 0;
              while (true) {
                  x += 1;
                  x *= 2;
                  x %= 1000000;
              }
          }).start();
      }
    return "ok";
  }
}