package org.xebia.tst.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * 
 * Exception handlers
 * @author elmehdi
 *
 */
@ControllerAdvice  
@RestController 
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	/**
	 * 
	 * handel file parser exception
	 * 
	 * @param e
	 * @return
	 */
    @ExceptionHandler(value = FileParsException.class)  
    public ResponseEntity<ErrorData>  handleBaseException(FileParsException e){  
    	ErrorData errorData = new ErrorData();
    	errorData.setIdError(ErrorsId.FILE_PARSING_ERROR);
    	errorData.setStatus(HttpStatus.BAD_REQUEST);
    	errorData.setMessageError(e.getMessage());
    	errorData.setDebugInfo(e.getLocalizedMessage());
    	return new ResponseEntity<ErrorData>(errorData, HttpStatus.BAD_REQUEST);
    }  
    
    
    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorData errorData = new ErrorData();
    	errorData.setMessageError("Json malformed");
    	errorData.setIdError(ErrorsId.JSON_ERROR);
    	errorData.setDebugInfo(ex.getLocalizedMessage());
    	errorData.setStatus(HttpStatus.BAD_REQUEST);
        return  new ResponseEntity<>(errorData, HttpStatus.BAD_REQUEST);
    }

    /**
     * handel other exceptions
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)  
    public ResponseEntity<ErrorData>  handleBaseException(Exception e){  
    	ErrorData errorData = new ErrorData();
    	errorData.setMessageError(e.getMessage());
    	errorData.setIdError(ErrorsId.INTERNAL_SERVER_ERROR);
    	errorData.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    	errorData.setDebugInfo(e.getLocalizedMessage());
    	return new ResponseEntity<ErrorData>(errorData, HttpStatus.INTERNAL_SERVER_ERROR);
    }  
    
    
    
    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param ex      the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(  MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request) {
    	ErrorData errorData = new ErrorData();
    	errorData.setIdError(ErrorsId.JSON_ERROR);
    	errorData.setMessageError("Validation error");
    	errorData.setDebugInfo(ex.getLocalizedMessage());
    	errorData.setStatus(HttpStatus.BAD_REQUEST);
    	return new ResponseEntity<>(errorData, HttpStatus.BAD_REQUEST);
    }
}
