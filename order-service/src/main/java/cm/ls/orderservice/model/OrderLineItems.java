package cm.ls.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "t_order_line_items")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineItems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orderLineItem")
    private Long id;
    private String skuCode;
    private BigDecimal prix;
    private Integer qte;
}
