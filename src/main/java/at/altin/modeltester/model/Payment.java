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
public class Payment implements Serializable {
    private Long id;

    private PurchaseOrder orderPosition;

    private String paymentMethod;

    private String paymentStatus;

    private Double amount;

    private String transactionId;

    private String description;
}
