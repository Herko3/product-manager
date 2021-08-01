package productmanager.suppliers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class SupplierService {

    private SupplierRepository repository;

    private ModelMapper mapper;

    public List<SupplierDto> listSuppliers() {
        return repository.findAll().stream()
                .map(s->mapper.map(s,SupplierDto.class))
                .toList();
    }

    public SupplierDto findSupplierById(long id) {
        return mapper.map(getSupplierById(id),SupplierDto.class);
    }

    private Supplier getSupplierById(long id){
        return repository.findById(id).orElseThrow(()-> new SupplierNotFoundException(id));
    }

    public SupplierDto createSupplier(CreateSupplierCommand command) {
        Supplier supplier = new Supplier(command.getName(), command.getCountry(), command.getAddress(), command.getCurrency());
        repository.save(supplier);
        return mapper.map(supplier,SupplierDto.class);
    }

    @Transactional
    public SupplierDto updateSupplier(long id, UpdateSupplierCommand command) {
        Supplier supplier = getSupplierById(id);
        supplier.update(command);
        return mapper.map(supplier,SupplierDto.class);
    }

    public void deleteSupplier(long id) {
        repository.deleteById(id);
    }
}
