package br.com.dginx.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.dginx.model.Person;

public interface PersonRepository extends MongoRepository<Person, String> {

	Optional<Person> findByCpf(Long cpf);

}
