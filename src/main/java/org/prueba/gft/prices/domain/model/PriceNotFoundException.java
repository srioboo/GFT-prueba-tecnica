package org.prueba.gft.prices.domain.model;

public class PriceNotFoundException extends Exception {

	public PriceNotFoundException() {
		super("Price not found Exception");
	}

	public PriceNotFoundException(String message) {
		super(message);
	}
}
