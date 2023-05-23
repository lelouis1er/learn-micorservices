package cm.ls.productservice.productservice.repository;

import cm.ls.productservice.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
