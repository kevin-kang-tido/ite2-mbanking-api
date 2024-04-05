package co.istad.ite2mbanking.exception;

import co.istad.ite2mbanking.base.BasedError;
import co.istad.ite2mbanking.base.BasedErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BasedErrorResponse handleValidationErrors(MethodArgumentNotValidException ex){

        BasedError<List<?>> basedError = new BasedError<>();

        List<Map<String, Object>> errors = new ArrayList<>();


        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> {
                    Map<String, Object> error = new HashMap<>();
                    error.put("field", fieldError.getField());
                    error.put("reason", fieldError.getDefaultMessage());
                    errors.add(error);
                });
        basedError.setCode(HttpStatus.BAD_REQUEST.getReasonPhrase());
        basedError.setDescription(errors);

        return  new BasedErrorResponse(basedError);

    }

}
