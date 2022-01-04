package br.com.josias.crud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

	/**
	 * @author https://github.com/Jvnyor
	 */
	
	private static final long serialVersionUID = 835673783485833989L;
	
	@Id
	@Column(unique=true,length = 11)
	@NotNull
	@NotEmpty
	private String cpf;
	@Column(length=50)
	@NotNull
	@NotEmpty
	private String name;
}
