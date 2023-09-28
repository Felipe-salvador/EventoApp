package com.eventoapp.Exceptions;

import java.io.Serializable;

public class ServiceExc extends Exception implements Serializable{
	private static final long serialVersionUID = 1L;

	public ServiceExc(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
