package pe.edu.galaxy.training.java.sb.arq.layered.api_be_reclamos.service.base;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 2886621070626913719L;
	

	public ServiceException() {
		
	}

	public ServiceException(String message) {
		super(message);
		
	}

	public ServiceException(Throwable cause) {
		super(cause);
		
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
