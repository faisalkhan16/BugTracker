package com.codebyte.bugservice.validation;

import com.codebyte.bugservice.dto.CommentRequestDTO;
import com.codebyte.bugservice.exception.RequestValidationException;
import com.codebyte.bugservice.util.Util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CommentRequestValidator implements ConstraintValidator<CommentRequestConstraint, CommentRequestDTO> {

    @Override
    public void initialize(CommentRequestConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CommentRequestDTO commentRequestDTO, ConstraintValidatorContext context) {

        if(Util.isNullOrEmptyString(String.valueOf(commentRequestDTO.getBugId()))){
            throw new RequestValidationException("bug_id is required");
        }

        if(Util.isNullOrEmptyString(commentRequestDTO.getDescription())){
            throw new RequestValidationException("description is required");
        }

        return true;
    }
}
