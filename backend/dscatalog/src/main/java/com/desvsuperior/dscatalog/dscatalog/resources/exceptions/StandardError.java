package com.desvsuperior.dscatalog.dscatalog.resources.exceptions;

import java.time.Instant;

public class StandardError {
	
	
	private Instant timestamp;
	private int status;
	private String error;
	private String mmessage;
    private String path;
    
    public StandardError() {
    	
    	
    	
    	
    	
    }

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMmessage() {
		return mmessage;
	}

	public void setMmessage(String mmessage) {
		this.mmessage = mmessage;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
