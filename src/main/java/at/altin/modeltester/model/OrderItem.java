package at.altin.modeltester.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author altin
 * @since 2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {
    private Long id;
    private PurchaseOrder purchaseOrder;
    private Product product;
    private Integer quantity;
    private Double price;
}
