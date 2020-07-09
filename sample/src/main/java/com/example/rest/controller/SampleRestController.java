package com.example.rest.controller;

import static com.example.rest.constant.SampleAppConstants.POST_SAMPLE_REQUEST_URL;
import static com.example.rest.constant.SampleAppConstants.SAMPLE_REQUEST;
import static com.example.rest.constant.SampleAppConstants.VALIDATION_FAIL_MSG;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.model.SampleRequest;
import com.example.rest.model.SampleResponse;
import com.example.rest.util.StringUtil;
import com.example.rest.validator.SampleValidtor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SampleRestController {
	
	private final SampleValidtor sampleValidtor;
	
	@InitBinder
	public void initSampleValidatorBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(sampleValidtor);
	}
	
	//@ApiOperation(value = "Sample Request Post RestAPI Call",
	//		notes = "API for POST Call")
	@PostMapping(path = POST_SAMPLE_REQUEST_URL)
	public ResponseEntity<SampleResponse> postSampleRequest(@Valid @NotNull @RequestBody SampleRequest sampleRequest,  
																						 BindingResult bindingResult){
		log.info("postSampleRequest call entred with request: {}", sampleRequest);
		
		//validate json input request
		if(bindingResult.hasErrors()) {
			String errMsg = StringUtil.getErrorMsgFromValidator(bindingResult);
			log.error("{} Validation of input: {} failed for reason: {}", SAMPLE_REQUEST, sampleRequest, errMsg);
			return new ResponseEntity<> (SampleResponse.builder()
					.statusCd(HttpStatus.BAD_REQUEST.value())
					.errorMsg(VALIDATION_FAIL_MSG + errMsg)
					.build(),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
	}
	 
}
