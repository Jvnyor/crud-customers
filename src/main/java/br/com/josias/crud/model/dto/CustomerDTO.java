package br.com.josias.crud.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import br.com.josias.crud.model.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	
	@NotEmpty(message="The CPF cannot be empty")
	@Schema(maxLength = 11,minLength = 11,nullable = false,description = "CPF Number of customer", example = "12345678910", required = true)
	private String cpf;
	
	@NotEmpty(message="The Name cannot be empty")
	@Schema(maxLength = 50,nullable = false,description = "Name of customer", example = "Josias Junior", required = true)
	private String name;
	
	@Email(message = "E-mail is not valid")
	@NotEmpty(message = "The E-mail cannot be empty")
	@Schema(maxLength = 100,nullable = false,description = "E-mail of customer", example = "josias@mail.com", required = true)
	private String email;
	
	@Email(message = "Address is not valid")
	@NotEmpty(message = "The Address cannot be empty")
	@Schema(nullable = false,description = "Address of customer", required = true)
	private Address address;
	
}
