package at.altin.modeltester.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author altin
 * @since 2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    private static final long serialVersionUID = 7876904359463936168L;
    private Long id;
    private Customer customer;
    private String type;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", customer=" + customer +
                ", type='" + type + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(customer, address.customer) && Objects.equals(type, address.type) && Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(country, address.country) && Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, type, street, city, state, country, zipCode);
    }

    public Address copy() {
        Address copy = new Address();
        copy.id = this.id;
        copy.customer = this.customer;
        copy.type = this.type;
        copy.street = this.street;
        copy.city = this.city;
        copy.state = this.state;
        copy.country = this.country;
        copy.zipCode = this.zipCode;
        return copy;
    }
}
