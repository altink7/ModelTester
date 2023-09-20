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
public class Product implements Serializable {
    private Long id;

    private String brand;

    private String model;

    private String description;

    private String imageUrl;

    private String price;
}
