package productmanager.suppliers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@Tag(name = "Operations on suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService service;

    @GetMapping
    @Operation(summary = "gives back all suppliers")
    public List<SupplierDto> listSuppliers() {
        return service.listSuppliers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "gives back an exact supplier")
    @ApiResponse(responseCode = "404", description = "supplier not found")
    public SupplierDto findSupplierById(@PathVariable("id") long id) {
        return service.findSupplierById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "creates a new supplier")
    public SupplierDto createSupplier(@Valid @RequestBody CreateSupplierCommand command) {
        return service.createSupplier(command);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "updates a single supplier")
    @ApiResponse(responseCode = "404", description = "Supplier not found")
    public SupplierDto updateSupplier(@PathVariable("id") long id, @Valid @RequestBody UpdateSupplierCommand command) {
        return service.updateSupplier(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "deletes a single supplier")
    @ApiResponse(responseCode = "404", description = "Supplier not found")
    public void deleteSupplier(@PathVariable("id") long id) {
        service.deleteSupplier(id);
    }
}
