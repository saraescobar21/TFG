/**
 * 
 */
package excepciones;

@SuppressWarnings("serial")
public class ClinicaException extends Exception {

	int codigoError;
	public ClinicaException() {
		
	}
	
	public ClinicaException(String message) {
		super(message);
		this.codigoError=codigoError;
	}
	public int getErrorCode() {
		return codigoError;
	}
}
