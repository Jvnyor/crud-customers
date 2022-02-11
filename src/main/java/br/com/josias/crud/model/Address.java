package br.com.josias.crud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3806649944055413472L;

	@Id
	@Column(nullable = false, unique = true)
	@JsonIgnore
	private String id;
	
	@OneToOne(
			fetch = FetchType.LAZY, optional = false)
	@JoinColumn(
			name = "customer_id", nullable = false)
	@JsonIgnore
	private Customer customer;
	
	@Column(length = 100, nullable = false)
	@Size(max = 100,message = "Street is not valid")
	@NotEmpty
	private String street;
	@Column(length = 8, nullable = false)
	@Size(min = 8,max = 8,message = "Zip code is not valid")
	@NotEmpty
	private String zipCode;
	@Column(length = 5, nullable = false)
	@Size(max = 5,message = "Number is not valid")
	@NotEmpty
	private int number;
	@Column(length = 100, nullable = false)
	@Size(max = 100,message = "Complement is not valid")
	@NotEmpty
	private String complement;
	@Column(length = 100, nullable = false)
	@Size(max = 100,message = "City is not valid")
	@NotEmpty
	private String city;
	@Column(length = 2, nullable = false)
	@Size(min = 2,max = 2,message = "State is not valid")
	@NotEmpty
	private String state;
	@Column(length = 100, nullable = false)
	@Size(max = 100,message = "Country is not valid")
	@NotEmpty
	private String country;
	
	public Address(String id, @Size(max = 100, message = "Street is not valid") @NotEmpty String street,
			@Size(min = 8, max = 8, message = "Zip code is not valid") @NotEmpty String zipCode,
			@Size(max = 5, message = "Number is not valid") @NotEmpty int number,
			@Size(max = 100, message = "Complement is not valid") @NotEmpty String complement,
			@Size(max = 100, message = "City is not valid") @NotEmpty String city,
			@Size(min = 2, max = 2, message = "State is not valid") @NotEmpty String state,
			@Size(max = 100, message = "Country is not valid") @NotEmpty String country) {
		super();
		this.id = id;
		this.street = street;
		this.zipCode = zipCode;
		this.number = number;
		this.complement = complement;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	
}
