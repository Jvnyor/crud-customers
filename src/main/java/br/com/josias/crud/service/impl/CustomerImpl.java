package br.com.josias.crud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.josias.crud.model.Customer;
import br.com.josias.crud.model.dto.CustomerDTO;
import br.com.josias.crud.repository.CustomerRepository;
import br.com.josias.crud.service.CustomerService;

@Service
public class CustomerImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer findById(String cpf) {
		// TODO Auto-generated method stub
		return customerRepository.findById(cpf)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"CPF not found"));
	}

	@Override
	public List<Customer> listAllNonPageable() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Transactional
	@Override
	public Customer save(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		if (cpfExist(customerDTO.getCpf()) || customerDTO.getCpf().length() != 11 || !stringIsNumeric(customerDTO.getCpf())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else if (customerDTO.getName().length()<5 || !stringIsCharacter(customerDTO.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
			return customerRepository.save(customerDTO.toEntity());
		}
	}

	@Override
	public Customer replace(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		if (!stringIsCharacter(customerDTO.getName()) || customerDTO.getName().length()<10) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
			return customerRepository.save(customerDTO.toEntity());
		}
	}

	@Override
	public void delete(String cpf) {
		// TODO Auto-generated method stub
		customerRepository.delete(findById(cpf));
	}

	@Override
	public List<Customer> findNameWithLike(String name) {
		// TODO Auto-generated method stub
		return customerRepository.findNameWithLike(name);
	}

	@Override
	public boolean cpfExist(String cpf) {
		// TODO Auto-generated method stub
		return customerRepository.findByCpf(cpf) != null;
	}

	@Override
	public Page<Customer> listAllPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		return customerRepository.findAll(pageable);
	}

	@Override
	public boolean stringIsNumeric(String string) {
        boolean isNumeric = true;
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
                isNumeric = false;
            }
        }
        return isNumeric;
	}

	@Override
	public boolean stringIsCharacter(String string) {
		// TODO Auto-generated method stub
		boolean isCharacter = true;
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isAlphabetic(string.charAt(i)) && !Character.isSpaceChar(string.charAt(i))) {
            	isCharacter = false;
            }
        }
		return isCharacter;
	}

}
