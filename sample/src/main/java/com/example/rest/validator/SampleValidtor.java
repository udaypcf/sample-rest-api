package com.example.rest.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.rest.model.SampleAnotherReq;
import com.example.rest.model.SampleRequest;

@Component
public class SampleValidtor implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SampleRequest.class.equals(clazz) 
				|| SampleAnotherReq.class.equals(clazz);
	}
	

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof SampleRequest) {
			SampleRequest sampleReq = (SampleRequest)target;
			if( sampleReq.getBatchId() <= 0) {
				errors.rejectValue("batchId", "batchId-Cd", "batchId can't be zero or negative");
			}
			if(StringUtils.isEmpty(sampleReq.getUserCd())){
				errors.rejectValue("userCd", "userCd-Cd", "userCd can't be empty");	
			}			
		}
		if (target instanceof SampleAnotherReq) {
			
			SampleAnotherReq sampleAnotherReq = (SampleAnotherReq)target;
			if( sampleAnotherReq.getAge() <= 0) {
				errors.rejectValue("age", "age-Cd", "age can't be zero or negative");
			}
			if(StringUtils.isEmpty(sampleAnotherReq.getName())){
				errors.rejectValue("name", "name-Cd", "userCd can't be empty");	
			}
			if(StringUtils.isEmpty(sampleAnotherReq.getSex())){
				errors.rejectValue("sex", "sex-Cd", "sex can't be empty");	
			}
		}
				
	}

}
