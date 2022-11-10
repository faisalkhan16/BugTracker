package com.codebyte.bugservice.validation;

import com.codebyte.bugservice.dto.BugRequestDTO;
import com.codebyte.bugservice.exception.RequestValidationException;
import com.codebyte.bugservice.util.Util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class BugRequestValidator implements ConstraintValidator<BugRequestContraint, BugRequestDTO> {

    List<Integer> severityCodes = new ArrayList<>();
    List<Integer> statusCodes = new ArrayList<>();

    @Override
    public void initialize(BugRequestContraint annotation) {
        severityCodes.add(0);
        severityCodes.add(1);
        severityCodes.add(2);
        severityCodes.add(3);

        statusCodes.add(0);
        statusCodes.add(1);
        statusCodes.add(2);
    }

    @Override
    public boolean isValid(BugRequestDTO bugRequestDTO, ConstraintValidatorContext context) {

        if(Util.isNullOrEmptyString(bugRequestDTO.getName())){
            throw new RequestValidationException("name is required");
        }
        else{
            if(bugRequestDTO.getName().length() > 20){
                throw new RequestValidationException("name length should not be more than 20");
            }
        }

        if(Util.isNullOrEmptyString(bugRequestDTO.getDescription())){
            throw new RequestValidationException("description is required");
        }
        else{
            if(bugRequestDTO.getName().length() > 100){
                throw new RequestValidationException("description length should not be more than 100");
            }
        }

        if(!severityCodes.contains(bugRequestDTO.getSeverity())){
            throw new RequestValidationException("severity should be 0 = Low, 1 = Medium, 2 = High, 3 = Critical");
        }

        if(!statusCodes.contains(bugRequestDTO.getStatus())){
            throw new RequestValidationException("status should be 0 = Waiting, 1 = InProgress, 2 = Resolved");
        }

        if(Util.isNullOrEmptyString(bugRequestDTO.getProductId())){
            throw new RequestValidationException("product_id is required");
        }else{
            if(bugRequestDTO.getProductId().length() != 26){
                throw new RequestValidationException("invalid product_id ULID");
            }
        }

        if(Util.isNullOrEmptyString(bugRequestDTO.getAssignByUserId())){
            throw new RequestValidationException("assign_by_user_id is required");
        }else{
            if(bugRequestDTO.getAssignByUserId().length() != 26){
                throw new RequestValidationException("invalid assign_by_user_id ULID");
            }
        }

        if(Util.isNullOrEmptyString(bugRequestDTO.getAssignToUserId())){
            throw new RequestValidationException("assign_to_user_id is required");
        }else{
            if(bugRequestDTO.getAssignToUserId().length() != 26){
                throw new RequestValidationException("invalid assign_to_user_id ULID");
            }
        }

        return true;
    }
}
