package cm.ls.productservice.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "t_produits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_produit")
    private Long id;
    private String nom;
    private String description;
    private BigDecimal prix;

}
