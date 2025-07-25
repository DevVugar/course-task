package az.ingress.model.entity;


import az.ingress.enums.ProductStatus;
import lombok.*;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@EqualsAndHashCode(of = "id")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus=ProductStatus.ACTIVE;
 
    private double price;

    private int stock;



    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime createdAt = LocalDateTime.now();


}
