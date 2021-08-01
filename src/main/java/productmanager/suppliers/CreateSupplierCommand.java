package productmanager.suppliers;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupplierCommand {

    @NotBlank
    @Size(max = 255)
    @Schema(description = "the name of the supplier", example = "GOKI")
    private String name;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "the country of the supplier", example = "Germany")
    private String country;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "the address of the supplier", example = "Hauptstraße 13 - 16, 21514 Güster")
    private String address;

    @Schema(description = "the currency of the invoice", example = "EUR")
    private Currency currency;
}
