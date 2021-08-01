package productmanager.products;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class ProductNotFoundException extends AbstractThrowableProblem {

    public ProductNotFoundException(long id) {
        super(URI.create("products/not-found"),
                "not found",
                Status.NOT_FOUND,
                "Product not found with id: " + id);
    }
}
