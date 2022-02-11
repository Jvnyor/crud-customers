package br.com.josias.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.josias.crud.model.Customer;

@Service
public interface CustomerService {
	Page<Customer> listAllPageable(Pageable pageable);
	List<Customer> findNameWithLike(String name);
	Customer findById(String id);
	List<Customer> listAllNonPageable();
	Customer save(Customer customer);
	Customer replace(Customer customer);
	void delete(String id);
	boolean cpfExist(String id);
	boolean stringIsNumeric(String s);
	boolean stringIsCharacter(String s);
}
