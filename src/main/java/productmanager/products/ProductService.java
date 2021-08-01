package productmanager.products;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productmanager.suppliers.Supplier;
import productmanager.suppliers.SupplierNotFoundException;
import productmanager.suppliers.SupplierRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository repository;
    private SupplierRepository supplierRepository;

    private ModelMapper mapper;

    public List<ProductDto> listAllProducts(Optional<String> namePart) {
        List<Product> result;

        if (namePart.isPresent()) {
            result = repository.findAllByNameContains(namePart.get());
        } else {
            result = repository.findAll();
        }
        return result.stream()
                .map(p -> mapper.map(p, ProductDto.class))
                .toList();
    }

    public ProductDto findProductById(long id) {
        return mapper.map(getById(id), ProductDto.class);
    }

    private Product getById(long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional
    public ProductDto createProduct(CreateProductCommand command) {
        Supplier supplier = getSupplierById(command.getSupplierId());
        Product product = new Product(command.getArticleNumber(), command.getName(), command.getDescription(),
                command.getType(), command.getNetPrice(), command.getGrossPrice());
        product.setSupplier(supplier);
        repository.save(product);
        return mapper.map(product, ProductDto.class);
    }

    @Transactional
    public ProductDto updateProduct(long id, UpdateProductCommand command) {
        Product product = getById(id);
        if(command.getSupplierId() != null){
            Supplier supplier = getSupplierById(command.getSupplierId());
            product.setSupplier(supplier);
        }
        product.update(command);
        return mapper.map(product,ProductDto.class);
    }

    private Supplier getSupplierById(long id){
        return supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException(id));
    }

    public void deleteProduct(long id) {
        repository.deleteById(id);
    }
}
