package br.com.josias.crud.model;

import lombok.Data;

@Data
public class AddressDTO {

	private String street;
	private String postalCode;
	private int number;
	private String addressReference;
	private String neighborhood;
	private String city;
	private String state;
	private String country;

}
