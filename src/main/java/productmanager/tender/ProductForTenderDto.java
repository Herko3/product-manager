package productmanager.tender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForTenderDto {

    private String articleNumber;
    private String name;
    private String description;
    private long grossPrice;
}
