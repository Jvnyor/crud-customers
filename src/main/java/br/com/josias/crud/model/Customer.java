package br.com.josias.crud.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
public class Customer implements Serializable {

	/**
	 * @author Josias Junior https://github.com/Jvnyor
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 11)
	@NotEmpty
	private String cpf;

	@NotEmpty
	private String name;

	@Email(message = "E-mail is not valid")
	private String email;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
	private Address addresses;

}
