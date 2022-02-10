package br.com.josias.crud.model.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	
	@NotEmpty(message="The Street cannot be empty")
	@Schema(maxLength = 100,nullable = false,description = "Street address of customer", example = "", required = true)
	private String street;
	@NotEmpty(message="The Zip Code cannot be empty")
	@Schema(maxLength = 8,minLength = 8,nullable = false,description = "Zip Code address of customer", example = "0000000", required = true)
	private String zipCode;
	@NotEmpty(message="The Number cannot be empty")
	@Schema(maxLength = 5,nullable = false,description = "Number address of customer", example = "", required = true)
	private int number;
	@NotEmpty(message="The Complement cannot be empty")
	@Schema(maxLength = 100,nullable = false,description = "Complement address of customer", example = "", required = true)
	private String complement;
	@NotEmpty(message="The City cannot be empty")
	@Schema(maxLength = 100,nullable = false,description = "City address of customer", example = "New York", required = true)
	private String city;
	
	@NotEmpty(message="The State cannot be empty")
	@Schema(maxLength = 2,minLength = 2,nullable = false,description = "State address of customer", example = "NY", required = true)
	private String state;
	@NotEmpty(message="The Country cannot be empty")
	@Schema(maxLength = 100,nullable = false,description = "Country address of customer", example = "United States", required = true)
	private String country;

}
