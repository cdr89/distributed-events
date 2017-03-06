package it.caldesi.producer.exceptions;

public class ThrowableBean {

	private String status = "Error";
	private String message;

	public ThrowableBean(Throwable throwable) {
		setMessage(throwable.getMessage());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
