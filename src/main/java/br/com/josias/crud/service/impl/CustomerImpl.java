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
import br.com.josias.crud.model.dto.AddressDTO;
import br.com.josias.crud.model.dto.CustomerDTO;
import br.com.josias.crud.repository.CustomerRepository;
import br.com.josias.crud.service.CustomerService;

@Service
public class CustomerImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer findById(Long id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id)
				.orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST,"CPF not found"));
	}

	@Override
	public List<Customer> listAllNonPageable() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Transactional
	@Override
	public Customer save(CustomerDTO customerDTO, AddressDTO addressDTO) {
		// TODO Auto-generated method stub
		Customer customerToSave = Customer.builder()
				.cpf(customerDTO.getCpf())
				.name(customerDTO.getName())
				.email(customerDTO.getEmail())
				.address(Address.builder()
						.street(addressDTO.getStreet())
						.number(addressDTO.getNumber())
						.zipCode(addressDTO.getZipCode())
						.complement(addressDTO.getComplement())
						.city(addressDTO.getCity())
						.country(addressDTO.getCountry())
						.build())
				.build();
		
		if (cpfExist(customerDTO.getCpf()) || customerDTO.getCpf().length() != 11 || !stringIsNumeric(customerDTO.getCpf())) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "CPF already exists in DB or CPF format are incorret");
		} else if (customerDTO.getName().length()<5 || !stringIsCharacter(customerDTO.getName())) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Name size or name format are incorret");
		} else {
			return customerRepository.save(customerToSave);
		}
	}

	@Override
	public Customer replace(Long id, CustomerDTO customerDTO, AddressDTO addressDTO) {
		// TODO Auto-generated method stub
		Customer customerToReplace = Customer.builder()
				.id(id)
				.cpf(customerDTO.getCpf())
				.name(customerDTO.getName())
				.email(customerDTO.getEmail())
				.address(Address.builder()
						.id(id)
						.street(addressDTO.getStreet())
						.number(addressDTO.getNumber())
						.zipCode(addressDTO.getZipCode())
						.complement(addressDTO.getComplement())
						.city(addressDTO.getCity())
						.country(addressDTO.getCountry())
						.build())
				.build();
		
		if (!stringIsCharacter(customerDTO.getName()) || customerDTO.getName().length()<5) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Name size or name format are incorret");
		} else {
			return customerRepository.save(customerToReplace);
		}
	}

	@Override
	public void delete(Long id) {
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
