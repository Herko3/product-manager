package productmanager.products;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByNameContains(String part);
    List<Product> findAllBySupplier_Id(long id);
    Optional<Product> findByArticleNumber(String articleNumber);
}
