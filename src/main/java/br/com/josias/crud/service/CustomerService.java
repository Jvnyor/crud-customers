package br.com.josias.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.josias.crud.model.Customer;
import br.com.josias.crud.model.dto.CustomerDTO;

@Service
public interface CustomerService {
	Page<Customer> listAllPageable(Pageable pageable);
	List<Customer> findNameWithLike(String name);
	Customer findById(String cpf);
	List<Customer> listAllNonPageable();
	Customer save(CustomerDTO customerDTO);
	Customer replace(CustomerDTO customerDTO);
	void delete(String cpf);
	boolean cpfExist(String cpf);
	boolean stringIsNumeric(String string);
	boolean stringIsCharacter(String string);
}
