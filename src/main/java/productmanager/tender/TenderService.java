package productmanager.tender;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productmanager.products.Product;
import productmanager.products.ProductRepository;
import productmanager.suppliers.NotFoundException;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@Service
public class TenderService {

    private TenderRepository repository;
    private ProductRepository productRepository;

    private ModelMapper mapper;

    public List<TenderDto> listTenders() {
        return repository.findAll().stream()
                .map(t -> mapper.map(t, TenderDto.class))
                .toList();
    }

    public TenderDto createTender(CreateTenderCommand command) {
        Tender tender = new Tender(command.getName(), command.getQuotationDate());
        List<Product> products = productRepository.findAllByArticleNumberIn(command.getArticleNumbers());
        tender.setProducts(products);
        repository.save(tender);
        return mapper.map(tender, TenderDto.class);
    }

    public TenderDto findTender(long id) {
        return mapper.map(getTenderById(id), TenderDto.class);
    }

    private Tender getTenderById(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(URI.create("tenders/not-found"), "Tender not found with id: " + id));
    }

    public void deleteTender(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public TenderDto updateTender(long id, UpdateTenderCommand command) {
        Tender tender = getTenderById(id);
        if (command.getArticleNumbers() != null) {
            List<Product> products = productRepository.findAllByArticleNumberIn(command.getArticleNumbers());
            tender.setProducts(products);
        }
        tender.update(command.getName(), command.getQuotationDate());
        return mapper.map(tender, TenderDto.class);
    }
}
