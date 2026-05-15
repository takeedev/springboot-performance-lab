package takee.dev.performance.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Controller {

    private final TransactionRepository transactionRepository;

    @GetMapping("/slow")
    public String slow() throws InterruptedException {
        for (int j = 0; j < 20 ; j++) {
            transactionRepository.findAll(Pageable.ofSize(10));
            Thread.sleep(1000);
            log.info("count {}", j);
        }
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