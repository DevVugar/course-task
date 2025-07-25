package az.ingress.repository;


import az.ingress.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p from Product p where p.name= :name")
    Product getProductByName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.price > :price")
    List<Product> findProductsWithPriceGreaterThan(@Param("price") BigDecimal price);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findProductsByCategoryId(@Param("categoryId") Long categoryId);


    @Query(nativeQuery = true,value = "select * from product where id= :id")
    Product getProductById(Long id);




}
