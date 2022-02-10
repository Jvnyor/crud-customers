package br.com.josias.crud.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements Serializable {

	/**
	 * @author Josias Junior https://github.com/Jvnyor
	 */
	
	private static final long serialVersionUID = 835673783485833989L;
	
	@Id
	@Column(unique=true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 11)
	@Size(min = 11,max = 11,message = "CPF format are incorret")
	@NotEmpty
	private String cpf;
	
	@Column(length=50, nullable = false)
	@Size(max = 50,message = "Name is not valid")
	@NotEmpty
	private String name;
	
	@Email(message = "E-mail is not valid")
	@Column(length = 100, nullable = false)
	@Size(max = 100, message = "E-mail is not valid")
	private String email;
	
	@OneToOne(
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			mappedBy = "customer")
	@JoinColumn(
			name = "id")
	private Address address;
}
