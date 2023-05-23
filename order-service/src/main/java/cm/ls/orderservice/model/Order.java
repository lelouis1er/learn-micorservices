package cm.ls.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "t_order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemsList;

}
