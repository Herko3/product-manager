package productmanager.tender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenderDto {

    private long id;
    private String name;
    private LocalDate quotationDate;
    private List<ProductForTenderDto> products;
}
