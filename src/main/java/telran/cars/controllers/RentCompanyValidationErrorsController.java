package telran.cars.controllers;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;
import static telran.cars.api.RentCompanyErrorMessage.*;

@ControllerAdvice
@Slf4j
public class RentCompanyValidationErrorsController 
{
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e)
	{
		log.error(e.getMessage());
		String message = e.getAllErrors().stream().map(er -> er.getDefaultMessage())
				.collect(Collectors.joining("; "));
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HandlerMethodValidationException.class)
	ResponseEntity<String> handlerMethodValidationException(HandlerMethodValidationException e)
	{
		log.error(e.getMessage());
		String message = e.getAllErrors().stream().map(er -> er.getDefaultMessage())
				.collect(Collectors.joining("; "));
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	ResponseEntity<String> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e)
	{
		log.error(e.getMessage());
		return new ResponseEntity<String>(TYPE_MISMATCH, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	ResponseEntity<String> httpMessageNotReadableException(HttpMessageNotReadableException e)
	{
		log.error(e.getMessage());
		return new ResponseEntity<String>(JSON_TYPE_MISMATCH, HttpStatus.BAD_REQUEST);
	}
}
