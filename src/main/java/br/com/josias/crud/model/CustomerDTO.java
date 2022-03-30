package br.com.josias.crud.model;

import lombok.Data;

@Data
public class CustomerDTO {

	private String cpf;
	private String name;
	private String email;
	private AddressDTO address;
}
