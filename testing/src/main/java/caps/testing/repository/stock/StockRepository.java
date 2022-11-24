package caps.testing.repository.stock;

import caps.testing.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findById(Long id);

    @Query(value = "select * from stock s where s.stock_id = ?1", nativeQuery = true)
    List<Stock> findAll(Long Stock_id);

}
