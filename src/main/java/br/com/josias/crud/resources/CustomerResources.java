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
import br.com.josias.crud.model.dto.CustomerDTO;
import br.com.josias.crud.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Tag(name = "crud-customers")
@RequestMapping("/customers")
@Slf4j
public class CustomerResources {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	@Operation(summary="List of all Customers paginated",description="List of all customers paginated",tags="{customer}")
	public ResponseEntity<Page<Customer>> listAllCustomersPageable(@ParameterObject Pageable pageable) {
		return ResponseEntity.ok(customerService.listAllPageable(pageable));
	}
	
	@GetMapping("/list")
	@Operation(summary="List of all Customers",description="List of all customers",tags="{customer}")
	public ResponseEntity<List<Customer>> listAllCustomersNonPageable() {
		return ResponseEntity.ok(customerService.listAllNonPageable());
	}
	
	@GetMapping("/find/{cpf}")
	@Operation(summary="Find customer by CPF",description="Find customer by CPF",tags="{customer}")
	public ResponseEntity<Customer> findCustomerByCpf(@PathVariable String cpf) {
		Customer customer = customerService.findById(cpf);
		log.info("Customer by ID: {}",customer);
		return ResponseEntity.ok(customer);
	}
	
	@GetMapping("/find")
	@Operation(summary="Find customers by name",description="Find customer by name (first name or last name)",tags="{customer}")
	public ResponseEntity<List<Customer>> findCustomerNameWithLike(@RequestParam String name) {
		List<Customer> customerNameWithLike = customerService.findNameWithLike(name);
		log.info("Customers by name: {}", customerNameWithLike);
		return ResponseEntity.ok(customerNameWithLike);
	}
	
	@PostMapping("/create")
	@Transactional
	@Operation(summary="Create customers",description="create customers (have cpf verification)",tags="{customer}")
	public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
		log.info("Customer saved: {}",customerDTO);
		return new ResponseEntity<>(customerService.save(customerDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/replace")
	@Operation(summary="Replace customers",description="replace customers (have cpf verification)",tags="{customer}")
	public ResponseEntity<Customer> replaceCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
		log.info("Customer replaced: {}",customerDTO);
		return ResponseEntity.ok(customerService.replace(customerDTO));
	}
	
	@DeleteMapping("/delete/{cpf}")
	@Operation(summary="Delete customers by CPF",description="delete customers by CPF",tags="{customer}")
	public ResponseEntity<Void> removeCustomer(@PathVariable String cpf) {
		Customer customer = customerService.findById(cpf);
		log.info("Customer deleted: {}",customer);
		customerService.delete(cpf);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
