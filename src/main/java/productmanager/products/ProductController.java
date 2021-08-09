package productmanager.products;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Operations on products")
public class ProductController {

    private final ProductService service;

    @GetMapping
    @Operation(summary = "gives back all products, optionally can be filtered in a query with namePart")
    public List<ProductDto> listAllProducts(@RequestParam Optional<String> namePart) {
        return service.listAllProducts(namePart);
    }

    @GetMapping("/{id}")
    @Operation(summary = "gives back a single product")
    @ApiResponse(responseCode = "404", description = "product doesn't exist")
    public ProductDto findProductById(@PathVariable("id") long id) {
        return service.findProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "creates a new product")
    @ApiResponse(responseCode = "404", description = "supplier not found")
    public ProductDto createProduct(@Valid @RequestBody CreateProductCommand command) {
        return service.createProduct(command);
    }

    @PutMapping("/{id}")
    @Operation(summary = "updates a single product")
    @ApiResponse(responseCode = "404", description = "product not found")
    public ProductDto updateProduct(@PathVariable("id") long id, @Valid @RequestBody UpdateProductCommand command) {
        return service.updateProduct(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "deletes a single product")
    @ApiResponse(responseCode = "404", description = "product not found")
    public void deleteProduct(@PathVariable ("id") long id){
        service.deleteProduct(id);
    }
}
