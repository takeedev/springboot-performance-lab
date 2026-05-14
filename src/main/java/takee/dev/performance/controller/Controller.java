package takee.dev.performance.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Controller {

    private final TransactionRepository transactionRepository;

  @GetMapping("/slow")
  public String slow() {
//      int cores = Runtime.getRuntime().availableProcessors();
//      for (int i = 0; i < cores; i++) {
//          new Thread(() -> {
//              long x = 0;
//              while (true) {
//                  x += 1;
//                  x *= 2;
//                  x %= 1000000;
//              }
//          }).start();
//      }
      var a = transactionRepository.findAll();
      a.forEach(e-> System.out.println(e));
    return "ok";
  }
}

@Repository
interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findAll();
}

@Getter
@Setter
@Entity
@Table(name = "transactions")
class Transaction {

    @Id
    private Long id;
    private int userId;
    private double amount;
    private String status;
    private Date createdAt;

}