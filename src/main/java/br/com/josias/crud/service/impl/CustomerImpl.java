package br.com.josias.crud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.josias.crud.exception.BadRequestException;
import br.com.josias.crud.model.Address;
import br.com.josias.crud.model.Customer;
import br.com.josias.crud.repository.CustomerRepository;
import br.com.josias.crud.service.CustomerService;

@Service
public class CustomerImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer findById(String id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id)
				.orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST,"ID not found"));
	}

	@Override
	public List<Customer> listAllNonPageable() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Transactional
	@Override
	public Customer save(Customer customer) {
		// TODO Auto-generated method stub
		
		Address address = new Address(customer.getCpf(),customer.getAddress().getStreet(), customer.getAddress().getZipCode(),customer.getAddress().getNumber(),customer.getAddress().getComplement(),customer.getAddress().getNeighborhood(),customer.getAddress().getCity(),customer.getAddress().getState(),customer.getAddress().getCountry());
		customer.setAddress(address);
		address.setCustomer(customer);
		
		if (cpfExist(customer.getCpf()) || customer.getCpf().length() != 11 || !stringIsNumeric(customer.getCpf())) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "ID already exists in DB or CPF Number format are incorret");
		} else if (customer.getName().length()<5 || !stringIsCharacter(customer.getName())) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Name size or name format are incorret");
		} else {
			return customerRepository.save(customer);
		}
	}

	@Override
	public Customer replace(Customer customer) {
		// TODO Auto-generated method stub
		Address address = new Address(customer.getCpf(),customer.getAddress().getStreet(), customer.getAddress().getZipCode(),customer.getAddress().getNumber(),customer.getAddress().getComplement(),customer.getAddress().getNeighborhood(),customer.getAddress().getCity(),customer.getAddress().getState(),customer.getAddress().getCountry());
		customer.setAddress(address);
		address.setCustomer(customer);
		
		if (!stringIsCharacter(customer.getName()) || customer.getName().length()<5) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Name size or name format are incorret");
		} else {
			return customerRepository.save(customer);
		}
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		customerRepository.delete(findById(id));
	}

	@Override
	public List<Customer> findNameWithLike(String name) {
		// TODO Auto-generated method stub
		if(customerRepository.findNameWithLike(name) != null) {
			return customerRepository.findNameWithLike(name);
		} else {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Name not found");
		}
	}

	@Override
	public boolean cpfExist(String id) {
		// TODO Auto-generated method stub
		return customerRepository.findByCpf(id) != null;
	}

	@Override
	public Page<Customer> listAllPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		return customerRepository.findAll(pageable);
	}

	@Override
	public boolean stringIsNumeric(String s) {
        boolean isNumeric = true;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                isNumeric = false;
            }
        }
        return isNumeric;
	}

	@Override
	public boolean stringIsCharacter(String s) {
		// TODO Auto-generated method stub
		boolean isCharacter = true;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isAlphabetic(s.charAt(i)) && !Character.isSpaceChar(s.charAt(i))) {
            	isCharacter = false;
            }
        }
		return isCharacter;
	}

}
