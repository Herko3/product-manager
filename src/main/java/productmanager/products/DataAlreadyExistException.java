package productmanager.products;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class DataAlreadyExistException extends AbstractThrowableProblem {

    public DataAlreadyExistException(String message) {
        super(URI.create("products/already-exist"),
                "Already Exist",
                Status.BAD_REQUEST,
                message);
    }
}
