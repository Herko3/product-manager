package productmanager.tender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import productmanager.products.CreateProductCommand;
import productmanager.products.ProductDto;
import productmanager.products.ProductType;
import productmanager.suppliers.CreateSupplierCommand;
import productmanager.suppliers.Currency;
import productmanager.suppliers.SupplierDto;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from tenders; delete from suppliers; delete from products;")
class TenderControllerIT {

    SupplierDto supplier;
    ProductDto product1;
    ProductDto product2;

    @Autowired
    TestRestTemplate template;

    @BeforeEach
    void init(){
        supplier = template.postForObject("/api/suppliers",
                new CreateSupplierCommand("Goki", "Germany", "Street 25", Currency.EUR), SupplierDto.class);

        product1 = template.postForObject("/api/products",
                new CreateProductCommand("28503", "Product 1", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 1257L, 2990L, supplier.getId()), ProductDto.class);

        product2 = template.postForObject("/api/products",
                new CreateProductCommand("28502", "Product 2", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 2458L, 5555L, supplier.getId()), ProductDto.class);
    }

    @Test
    void createThenList(){
        template.postForObject("/api/tenders",
                new CreateTenderCommand("Tender 101",
                        LocalDate.of(2015,12,15), List.of("28502","28503")),TenderDto.class);

        template.postForObject("/api/tenders",
                new CreateTenderCommand("Tender 103",
                        LocalDate.of(2015,12,15), List.of("28503")),TenderDto.class);

        List<TenderDto> result = template.exchange("/api/tenders",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TenderDto>>() {
                })
                .getBody();

        assertThat(result)
                .hasSize(2)
                .extracting(TenderDto::getName)
                .containsExactly("Tender 101", "Tender 103");
    }

    @Test
    void update(){
        TenderDto tender = template.postForObject("/api/tenders",
                new CreateTenderCommand("Tender 101",
                        LocalDate.of(2015,12,15), List.of("28502","28503")),TenderDto.class);

        template.put("/api/tenders/" + tender.getId(),
                new UpdateTenderCommand("Tender 505", LocalDate.of(2012,10,10),List.of("28503")));

        TenderDto result =template.getForObject("/api/tenders/" + tender.getId(),TenderDto.class);

        assertEquals("Tender 505", result.getName());

        assertThat(result.getProducts())
                .hasSize(1)
                .extracting(ProductForTenderDto::getName)
                .containsOnly("Product 1");
    }

    @Test
    void delete(){
        TenderDto tender = template.postForObject("/api/tenders",
                new CreateTenderCommand("Tender 101",
                        LocalDate.of(2015,12,15), List.of("28502","28503")),TenderDto.class);

        template.postForObject("/api/tenders",
                new CreateTenderCommand("Tender 103",
                        LocalDate.of(2015,12,15), List.of("28503")),TenderDto.class);

        template.delete("/api/tenders/" + tender.getId());

        List<TenderDto> result = template.exchange("/api/tenders",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<TenderDto>>() {
                        })
                .getBody();

        assertThat(result)
                .hasSize(1)
                .extracting(TenderDto::getName)
                .containsOnly("Tender 103");
    }

}