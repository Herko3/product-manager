package productmanager.suppliers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    private String address;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Supplier(String name, String country, String address, Currency currency) {
        this.name = name;
        this.country = country;
        this.address = address;
        this.currency = currency;
    }

    public void update(UpdateSupplierCommand command) {
        String nameUpdate = command.getName();
        String countryUpdate = command.getCountry();
        String addressUpdate = command.getAddress();
        Currency currencyUpdate = command.getCurrency();

        if(notEmpty(nameUpdate)){
            setName(nameUpdate);
        }

        if(notEmpty(countryUpdate)){
            setCountry(countryUpdate);
        }

        if(notEmpty(addressUpdate)){
            setAddress(addressUpdate);
        }

        if(currencyUpdate != null){
            setCurrency(currencyUpdate);
        }
    }

    private boolean notEmpty(String s){
        return s != null && !s.isBlank();
    }
}
