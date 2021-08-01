package productmanager.suppliers;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NotNull
public class UpdateSupplierCommand {

    @JsonProperty
    @Size(max = 255)
    @Schema(description = "the name of the , not required", example = "GOKI")
    private String name;

    @JsonProperty
    @Size(max = 255)
    @Schema(description = "the country of the supplier, not required", example = "Germany")
    private String country;

    @JsonProperty
    @Size(max = 255)
    @Schema(description = "the address of the supplier, not required", example = "Hauptstraße 13 - 16, 21514 Güster")
    private String address;

    @JsonProperty
    @Schema(description = "the currency of the invoice, not required", example = "EUR")
    private Currency currency;
}
