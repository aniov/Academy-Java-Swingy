package aniov.company.view.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Marius on 7/6/2017.
 */
public class ModelValidation {

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static Validator validator = factory.getValidator();
    private Set<ConstraintViolation<Object>> constraintViolations;

    public boolean isValid(Object object) {
        constraintViolations = validator.validate(object);
        return constraintViolations.isEmpty();
    }

    public List<String> violations() {
        List<String> messages = new ArrayList<>();
        for (ConstraintViolation violation : constraintViolations) {
            messages.add(violation.getMessage());
        }
        return messages;
    }
}
