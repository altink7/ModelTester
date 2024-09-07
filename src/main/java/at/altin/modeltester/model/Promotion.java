package at.altin.modeltester.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author altin
 * @since 2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Promotion implements Serializable {
    private Long id;
    private String code;
    private String description;
    private Double discountAmount;
    private LocalDate startDate;
    private LocalDate endDate;
}
