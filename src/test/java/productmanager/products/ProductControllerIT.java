package productmanager.products;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import productmanager.suppliers.CreateSupplierCommand;
import productmanager.suppliers.Currency;
import productmanager.suppliers.SupplierDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from suppliers; delete from products")
class ProductControllerIT {

    SupplierDto supplier1;
    SupplierDto supplier2;

    @Autowired
    TestRestTemplate template;

    @BeforeEach
    void init() {
        supplier1 = template.postForObject("/api/suppliers",
                new CreateSupplierCommand("Goki", "Germany", "Street 25", Currency.EUR), SupplierDto.class);

        supplier2 = template.postForObject("/api/suppliers",
                new CreateSupplierCommand("Legler", "Germany", "Street 12", Currency.EUR), SupplierDto.class);
    }

    @Test
    void testCreateThenList() {
        template.postForObject("/api/products",
                new CreateProductCommand("28503", "Montessori Torony", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 1257L, 2990L, supplier1.getId()), ProductDto.class);


        template.postForObject("/api/products",
                new CreateProductCommand("28502", "Montessori Torony", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 1257L, 2990L, supplier1.getId()), ProductDto.class);


        List<ProductDto> result = template.exchange("/api/products",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ProductDto>>() {
                        })
                .getBody();

        assertThat(result)
                .hasSize(2)
                .extracting(ProductDto::getArticleNumber)
                .containsExactly("28503", "28502");
    }

    @Test
    void testUpdate() {
        ProductDto product = template.postForObject("/api/products",
                new CreateProductCommand("28503", "Montessori Torony", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 1257L, 2990L, supplier1.getId()), ProductDto.class);

        template.put("/api/products/" + product.getId(),
                new UpdateProductCommand("28515", "book", "454 pages",
                        ProductType.BOOK, 1550L, 3150L, supplier2.getId()));

        ProductDto result = template.getForObject("/api/products/" + product.getId(), ProductDto.class);

        assertEquals(ProductType.BOOK, result.getType());
        assertEquals(3150, result.getGrossPrice());
        assertEquals("Legler", result.getSupplier().getName());
    }

    @Test
    void testDeleteProduct() {
        ProductDto product = template.postForObject("/api/products",
                new CreateProductCommand("28503", "Montessori Torony", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 1257L, 2990L, supplier1.getId()), ProductDto.class);


        template.postForObject("/api/products",
                new CreateProductCommand("28502", "Book", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 1257L, 2990L, supplier1.getId()), ProductDto.class);


        List<ProductDto> preDelete = template.exchange("/api/products",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ProductDto>>() {
                        })
                .getBody();

        assertThat(preDelete)
                .hasSize(2);

        template.delete("/api/products/" + product.getId());

        List<ProductDto> result = template.exchange("/api/products",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ProductDto>>() {
                        })
                .getBody();

        assertThat(result)
                .hasSize(1)
                .extracting(ProductDto::getName)
                .containsOnly("Book");
    }

    @Test
    void testDeleteSupplier() {
        template.postForObject("/api/products",
                new CreateProductCommand("28503", "Montessori Torony", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 1257L, 2990L, supplier2.getId()), ProductDto.class);


        template.postForObject("/api/products",
                new CreateProductCommand("28502", "Book", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 1257L, 2990L, supplier1.getId()), ProductDto.class);


        List<ProductDto> preDelete = template.exchange("/api/products",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ProductDto>>() {
                        })
                .getBody();

        assertThat(preDelete)
                .hasSize(2);

        template.delete("/api/suppliers/" + supplier1.getId());

        List<ProductDto> result = template.exchange("/api/products",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ProductDto>>() {
                        })
                .getBody();

        assertThat(result)
                .hasSize(1)
                .extracting(ProductDto::getDescription)
                .containsOnly("20 cm-es montessori torony");
    }

    @Test
    void testGetProductsForSupplier() {
        template.postForObject("/api/products",
                new CreateProductCommand("28503", "Montessori Torony", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 1257L, 2990L, supplier1.getId()), ProductDto.class);


        template.postForObject("/api/products",
                new CreateProductCommand("28502", "Montessori Torony", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 2458L, 5555L, supplier1.getId()), ProductDto.class);

        template.postForObject("/api/products",
                new CreateProductCommand("28504", "Montessori Torony", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 3528L, 4540L, supplier2.getId()), ProductDto.class);


        List<ProductDto> result = template.exchange("/api/suppliers/" + supplier2.getId() + "/products",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ProductDto>>() {
                        })
                .getBody();

        assertThat(result)
                .hasSize(1)
                .extracting(ProductDto::getNetPrice)
                .containsOnly(3528L);
    }

    @Test
    void testInvalidDoublePost() {
        template.postForObject("/api/products",
                new CreateProductCommand("28503", "Montessori Torony", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 1257L, 2990L, supplier1.getId()), ProductDto.class);

        Problem problem = template.postForObject("/api/products",
                new CreateProductCommand("28503", "Book", "454 pages",
                        ProductType.BOOK, 127L, 290L, supplier1.getId()), Problem.class);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());
    }

    @Test
    void testInvalidUpdateToExistingProduct() {
        ProductDto product = template.postForObject("/api/products",
                new CreateProductCommand("28503", "Montessori Torony", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 1257L, 2990L, supplier1.getId()), ProductDto.class);

        template.postForObject("/api/products",
                new CreateProductCommand("28502", "Montessori Torony", "20 cm-es montessori torony",
                        ProductType.WOODEN_TOY, 2458L, 5555L, supplier1.getId()), ProductDto.class);

        Problem problem = template.exchange("/api/products/" + product.getId(),
                        HttpMethod.PUT,
                        new HttpEntity<>(new UpdateProductCommand("28502", "book", "454 pages",
                                ProductType.BOOK, 1550L, 3150L, supplier2.getId())),
                        new ParameterizedTypeReference<Problem>() {
                        })
                .getBody();

        assertEquals(Status.BAD_REQUEST, problem.getStatus());
    }


}