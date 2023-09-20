package at.altin.modeltester.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author altin
 * @since 2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder implements Serializable {
    private Long id;

    private Customer customer;

    private List<OrderItem> orderItems;

    private String paymentMethod;

    private String paymentStatus;

    private Double totalAmount;

    private String orderType;
}
