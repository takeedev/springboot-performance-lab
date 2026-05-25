package takee.dev.performance.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Controller {

    private final TransactionRepository transactionRepository;
    private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    @GetMapping("/slow")
    public String slow() {
        long start = System.currentTimeMillis();
        log.info(">>> START request");
        List<CompletableFuture<Void>> futures = IntStream.range(0, 50)
                .mapToObj(i ->
                        CompletableFuture.runAsync(() -> {
                                    long taskStart = System.currentTimeMillis();
                                    log.info("Task " + i + " START");
                                    try {
                                        var result = transactionRepository.findAll(PageRequest.of(0, 10));
                                        log.info("Task " + i + " SUCCESS size=" + result.getSize());
                                    } catch (Exception e) {
                                        log.info("Task " + i + " ERROR: " + e.getMessage());
                                        throw e;
                                    } finally {
                                        long taskEnd = System.currentTimeMillis();
                                        log.info("Task " + i + " END time=" + (taskEnd - taskStart) + " ms");
                                    }
                                }, executorService)
                                .orTimeout(2, TimeUnit.SECONDS)
                                .exceptionally(ex -> {
                                    log.info("Task " + i + " TIMEOUT/FAIL: " + ex.getMessage());
                                    return null;
                                })
                )
                .toList();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        long end = System.currentTimeMillis();
        log.info("<<< END request totalTime={} ms", end - start);
        return "ok";
    }
}

@Repository
interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @NonNull
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