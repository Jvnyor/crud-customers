package br.com.josias.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.josias.crud.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

	Customer findByCpf(String cpf);

	@Query(value = "select * from customer where name like %:name%", nativeQuery = true)
	List<Customer> findNameWithLike(@Param("name") String name);

}
