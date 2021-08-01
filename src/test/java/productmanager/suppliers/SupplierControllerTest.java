package productmanager.suppliers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from suppliers")
class SupplierControllerTest {

    @Autowired
    TestRestTemplate template;

    @Test
    void testCreateThenList() {
        template.postForObject("/api/suppliers",
                new CreateSupplierCommand("Goki", "Germany", "Street 25", Currency.EUR), SupplierDto.class);

        template.postForObject("/api/suppliers",
                new CreateSupplierCommand("Legler", "Germany", "Street 12", Currency.EUR), SupplierDto.class);

        List<SupplierDto> result = template.exchange("/api/suppliers",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<SupplierDto>>() {
                        })
                .getBody();

        assertThat(result)
                .hasSize(2)
                .extracting(SupplierDto::getName)
                .containsExactly("Goki", "Legler");
    }

    @Test
    void invalidDataCreate() {
        Problem problem = template.postForObject("/api/suppliers",
                new CreateSupplierCommand("", "Germany", "Street 25", Currency.EUR), Problem.class);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());
    }

    @Test
    void testUpdate() {
        SupplierDto supplier = template.postForObject("/api/suppliers",
                new CreateSupplierCommand("Goki", "Germany", "Street 25", Currency.EUR), SupplierDto.class);

        template.put("/api/suppliers/" + supplier.getId(), new UpdateSupplierCommand("Legler", "Netherland", "Street 15", Currency.PLN));

        SupplierDto result = template.getForObject("/api/suppliers/" + supplier.getId(), SupplierDto.class);

        assertEquals(Currency.PLN, result.getCurrency());
        assertEquals("Netherland", result.getCountry());
    }

    @Test
    void testDelete() {
        SupplierDto toDelete = template.postForObject("/api/suppliers",
                new CreateSupplierCommand("Goki", "Germany", "Street 25", Currency.EUR), SupplierDto.class);

        template.postForObject("/api/suppliers",
                new CreateSupplierCommand("Legler", "Germany", "Street 12", Currency.EUR), SupplierDto.class);

        List<SupplierDto> preDelete = template.exchange("/api/suppliers",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<SupplierDto>>() {
                        })
                .getBody();

        assertThat(preDelete)
                .hasSize(2);

        template.delete("/api/suppliers/" + toDelete.getId());

        List<SupplierDto> result = template.exchange("/api/suppliers",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<SupplierDto>>() {
                        })
                .getBody();

        assertThat(result)
                .hasSize(1)
                .extracting(SupplierDto::getAddress)
                .containsOnly("Street 12");
    }

}