package productmanager.suppliers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDto {
    
    private long id;
    private String name;
    private String country;
    private String address;
    private Currency currency;

}
