package productmanager.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NotNull
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductCommand {

    @JsonProperty
    @Size(max = 25)
    @Schema(description = "the article number of the product, not a required argument", example = "28502")
    private String articleNumber;

    @JsonProperty
    @Size(max = 255)
    @Schema(description = "the name of the product, not a required argument", example = "Montessori torony")
    private String name;

    @JsonProperty
    @Size(max = 255)
    @Schema(description = "the description of the product, not a required argument", example = "20x20x20 cm")
    private String description;

    @JsonProperty
    @Schema(description = "the type of the product, not a required argument", example = "WOODEN_TOY")
    private ProductType type;

    @JsonProperty
    @Schema(description = "the net price of the product, not a required argument", example = "2990")
    private Long netPrice;

    @JsonProperty
    @Schema(description = "the gross price of the product, not a required argument", example = "2990")
    private Long grossPrice;

    @JsonProperty
    @Schema(description = "the id of the supplier, not a required argument")
    private Long supplierId;
}
