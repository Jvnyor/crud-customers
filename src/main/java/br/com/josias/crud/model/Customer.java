package br.com.josias.crud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

	/**
	 * @author Josias Junior https://github.com/Jvnyor
	 */
	
	private static final long serialVersionUID = 835673783485833989L;
	
	@Id
	@Column(unique=true,length = 11)
	@Size(min = 11,max = 11,message = "CPF format are incorret")
	@NotNull
	@NotEmpty
	private String cpf;
	@Column(length=50)
	@Size(min = 5,max = 50,message = "Name is not valid")
	@NotNull
	@NotEmpty
	private String name;
}
