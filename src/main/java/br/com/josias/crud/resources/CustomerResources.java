package br.com.josias.crud.resources;

import java.util.List;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.josias.crud.model.Customer;
import br.com.josias.crud.model.dto.AddressDTO;
import br.com.josias.crud.model.dto.CustomerDTO;
import br.com.josias.crud.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Tag(name = "Crud of Customers")
@RequestMapping("/customers")
@Slf4j
public class CustomerResources {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	@ApiResponse(responseCode = "200", description = "List paged of customers saved in DB")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "500", description = "Internal error")
	@Operation(summary="List of all Customers paginated",description="List of all customers paginated",tags="{customer}")
	public ResponseEntity<Page<Customer>> listAllCustomersPageable(@ParameterObject Pageable pageable) {
		return ResponseEntity.ok(customerService.listAllPageable(pageable));
	}
	
	@GetMapping("/list")
	@ApiResponse(responseCode = "200", description = "List of customers saved in DB")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "500", description = "Internal error")
	@Operation(summary="List of all Customers",description="List of all customers",tags="{customer}")
	public ResponseEntity<List<Customer>> listAllCustomersNonPageable() {
		return ResponseEntity.ok(customerService.listAllNonPageable());
	}
	
	@GetMapping("/find/{cpf}")
	@Operation(summary="Find customer by CPF",description="Find customer by CPF",tags="{customer}")
	@ApiResponse(responseCode = "200", description = "Customer by CPF finded in DB")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "500", description = "Internal error")
	public ResponseEntity<Customer> findCustomerByCpf(@PathVariable Long id) {
		Customer customer = customerService.findById(id);
		log.info("Customer by ID: {}",customer);
		return ResponseEntity.ok(customer);
	}
	
	@GetMapping("/find")
	@Operation(summary="Find customers by name",description="Find customer by name (first name or last name)",tags="{customer}")
	@ApiResponse(responseCode = "200", description = "Customer by name finded in DB")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "500", description = "Internal error")
	public ResponseEntity<List<Customer>> findCustomerNameWithLike(@RequestParam String name) {
		List<Customer> customerNameWithLike = customerService.findNameWithLike(name);
		log.info("Customers by name: {}", customerNameWithLike);
		return ResponseEntity.ok(customerNameWithLike);
	}
	
	@PostMapping("/create")
	@Transactional
	@Operation(summary="Create customers",description="create customers (have cpf verification)",tags="{customer}")
	@ApiResponse(responseCode = "201", description = "Customer registered in DB")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "500", description = "Internal error")
	public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerDTO customerDTO, AddressDTO addressDTO) {
		log.info("Customer saved: {}",customerDTO);
		return new ResponseEntity<>(customerService.save(customerDTO, addressDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/replace/{id}")
	@ApiResponse(responseCode = "200", description = "Customer replaced by CPF in DB")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "500", description = "Internal error")
	@Operation(summary="Replace customers",description="replace customers (have cpf verification)",tags="{customer}")
	public ResponseEntity<Customer> replaceCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDTO customerDTO, AddressDTO addressDTO) {
		log.info("Customer replaced, id: {}, Body: {}",id,customerDTO);
		return ResponseEntity.ok(customerService.replace(id,customerDTO,addressDTO));
	}
	
	@DeleteMapping("/delete/{cpf}")
	@ApiResponse(responseCode = "200", description = "Customer deleted by CPF in DB")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@ApiResponse(responseCode = "500", description = "Internal error")
	@Operation(summary="Delete customers by CPF",description="delete customers by CPF",tags="{customer}")
	public ResponseEntity<Void> removeCustomer(@PathVariable Long id) {
		Customer customer = customerService.findById(id);
		log.info("Customer deleted: {}",customer);
		customerService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
