package org.xebia.tst.exceptions;
/**
 * exception lever quand on a une erreur dans les donnees de fichier(format ...)
 * 
 * @author elmehdi
 *
 */
public class FileParsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public FileParsException( String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
