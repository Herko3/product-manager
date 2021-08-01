package productmanager.suppliers;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class SupplierNotFoundException extends AbstractThrowableProblem {
    public SupplierNotFoundException(long id) {
        super(URI.create("supplier/not-found"),
                "Not found",
                Status.NOT_FOUND,
                "Supplier not found with id: " + id);
    }
}
