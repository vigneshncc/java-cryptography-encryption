/**
 * NoKeyFoundException.java
 * com.piqube.encryption.exception
 * @author vignesh
 * @date 28-Apr-2014
 * @time 7:15:35 pm
 */
package org.gvignesh.encryption.exception;

/**
 * @author vignesh
 *
 */
public class NoKeyFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message = null;
    
    /**
     * 
     */
    public NoKeyFoundException() {
        super();
    }
 
    /**
     * @param message
     */
    public NoKeyFoundException(String message) {
        super(message);
        this.message = message;
    }
 
    /**
     * @param cause
     */
    public NoKeyFoundException(Throwable cause) {
        super(cause);
    }
 
    @Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
}