package productmanager.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private long id;
    private String articleNumber;
    private String name;
    private String description;
    private ProductType type;
    private long netPrice;
    private long grossPrice;
}
