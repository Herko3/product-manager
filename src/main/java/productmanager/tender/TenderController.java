package productmanager.tender;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tenders")
@RequiredArgsConstructor
@Tag(name = "Operations on tenders")
public class TenderController {

    private final TenderService service;

    @GetMapping
    @Operation(summary = "lists all tenders")
    public List<TenderDto> listTenders() {
        return service.listTenders();
    }

    @GetMapping("/{id}")
    @Operation(summary = "gives back a single tender")
    @ApiResponse(responseCode = "404", description = "tender not found")
    public TenderDto findTender(@PathVariable("id") long id) {
        return service.findTender(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "creates a new tender")
    public TenderDto createTender(@Valid @RequestBody CreateTenderCommand command) {
        return service.createTender(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "deletes a single tender")
    @ApiResponse(responseCode = "404", description = "Tender not found")
    public void deleteTender(@PathVariable("id") long id) {
        service.deleteTender(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "updates a tender")
    @ApiResponse(responseCode = "404", description = "Tender not found")
    public TenderDto updateTender(@PathVariable ("id") long id, @Valid @RequestBody UpdateTenderCommand command){
        return service.updateTender(id,command);
    }
}
