package br.com.josias.crud.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
public class Address implements Serializable {

	/**
	 * @author Josias Junior https://github.com/Jvnyor
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, unique = true)
	private String id;

	private String street;
	private String postalCode;
	private int number;
	private String addressReference;
	private String neighborhood;
	private String city;
	private String state;
	private String country;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonIgnore
	private Customer customer;

}
