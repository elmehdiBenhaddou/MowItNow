package org.xebia.tst.exceptions;

import org.springframework.http.HttpStatus;

/**
 * definition de l'erreur a evoyer a l'utilisateur
 * 
 * @author elmehdi
 *
 */
public class ErrorData {

	private HttpStatus  status;
	
	private String idError;
	
	private String messageError;
	
	private String debugInfo;
	
	
	public String getMessageError() {
		return messageError;
	}
	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}
	public String getIdError() {
		return idError;
	}
	public void setIdError(String idError) {
		this.idError = idError;
	}
	public String getDebugInfo() {
		return debugInfo;
	}
	public void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
