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
public class Customer implements Serializable {
    private Long id;
    private String name;
    private boolean company;
    private String email;
    private String phone;
    private String imageUrl;
}
