package main.java.binBeats.lib;

/**
 * Container for properties which represent the result of a validation
 */
public class ValidationResult {
	private Boolean isValid;
	private String message;
	
	public ValidationResult(Boolean isValid, String message) {
		this.isValid = isValid;
		this.message = message;
	}
	
	/**
	 * Indicates whether the validation was successful or not
	 * @return
	 */
	public Boolean isValid() {
		return this.isValid;
	}
	
	/**
	 * Returns a message which describes the validation (e.g. an error-message)
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}
}
