package productmanager.tender;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTenderCommand {

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 255)
    @Schema(name = "The name of the tender", example = "Sátoraljaújhely tender 39161000-8")
    private String name;

    @PastOrPresent(message = "date cannot be in the future")
    @Schema(name = "the date when the quotation was given", example = "2020-12-15")
    private LocalDate quotationDate;

    @NotEmpty
    @Schema(name = "list of the products in the tender", example = "[\"69048\",\"55048\"]")
    private List<String> articleNumbers;
}
