package name.aaponomarev.task3.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "branch not found")
public class BranchNotFoundException extends RuntimeException{

    public BranchNotFoundException() {
    }

    public BranchNotFoundException(String message) {
        super(message);
    }
}
