package productmanager.products;

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
public class CreateProductCommand {

    @NotBlank(message = "article number most not be empty")
    @Size(max = 25)
    @Schema(description = "the article number of the product", example = "28502")
    private String articleNumber;

    @NotBlank(message = "name most not be empty")
    @Size(max = 255)
    @Schema(description = "the name of the product", example = "Montessori torony")
    private String name;

    @JsonProperty
    @Size(max = 255)
    @Schema(description = "the description of the product", example = "20x20x20 cm")
    private String description;

    @NotNull(message = "the type must be filled out")
    @Schema(description = "the type of the product", example = "WOODEN_TOY")
    private ProductType type;

    @NotNull(message = "net price must be filled out")
    @Schema(description = "the net price of the product", example = "2990")
    private Long netPrice;

    @NotNull(message = "gross price must be filled out")
    @Schema(description = "the gross price of the product", example = "2990")
    private Long grossPrice;

    @NotNull(message = "supplier id must be filled out")
    @Schema(description = "the id of the supplier")
    private Long supplierId;
}
