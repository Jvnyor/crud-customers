package br.com.josias.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.josias.crud.model.Customer;
import br.com.josias.crud.model.dto.AddressDTO;
import br.com.josias.crud.model.dto.CustomerDTO;

@Service
public interface CustomerService {
	Page<Customer> listAllPageable(Pageable pageable);
	List<Customer> findNameWithLike(String name);
	Customer findById(Long id);
	List<Customer> listAllNonPageable();
	Customer save(CustomerDTO customerDTO, AddressDTO addressDTO);
	Customer replace(Long id, CustomerDTO customerDTO, AddressDTO addressDTO);
	void delete(Long id);
	boolean cpfExist(String cpf);
	boolean stringIsNumeric(String s);
	boolean stringIsCharacter(String s);
}
