package com.furkancetiner.ws.error;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;


@RestController
public class ErrorHandler implements ErrorController{
	
	@Autowired
	private ErrorAttributes errorAttributes;
	
	/*@RequestMapping("/error")
	ApiError handleError(WebRequest webRequest) {
	//Map<String, Object> attributes=this.errorAttributes.getErrorAttributes(webRequest, options);
	}
	*/
	public String getErrorPath() {
		return "/error";
	}
}
