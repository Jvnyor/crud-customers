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
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Customer implements Serializable {

	/**
	 * @author Josias Junior https://github.com/Jvnyor
	 */
	
	private static final long serialVersionUID = 835673783485833989L;
	
	@Id
	@Column(name = "id",unique=true,nullable = false, length = 11)
	@Size(min = 11,max = 11,message = "CPF format are incorret")
	@NotEmpty
	@Schema(maxLength = 11,minLength = 11,nullable = false,description = "CPF Number of customer", example = "12345678910", required = true)
	private String cpf;
	
	@Column(length=50, nullable = false)
	@Size(max = 50,message = "Name is not valid")
	@NotEmpty
	@Schema(maxLength = 50,nullable = false,description = "Name of customer", example = "Josias Junior", required = true)
	private String name;
	
	@Email(message = "E-mail is not valid")
	@Column(length = 100, nullable = false)
	@Size(max = 100, message = "E-mail is not valid")
	@Schema(maxLength = 100,nullable = false,description = "E-mail of customer", example = "josias@mail.com", required = true)
	private String email;
	
	@OneToOne(
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			mappedBy = "customer")
	@Schema(nullable = false,description = "Address of customer", required = true)
	private Address address;

	public Customer(@Size(min = 11, max = 11, message = "CPF format are incorret") @NotEmpty String cpf,
			@Size(max = 50, message = "Name is not valid") @NotEmpty String name,
			@Email(message = "E-mail is not valid") @Size(max = 100, message = "E-mail is not valid") String email) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.email = email;
	}
	
}
