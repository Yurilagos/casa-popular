package br.com.dginx.service;

import br.com.dginx.dto.PersonDTO;
import br.com.dginx.exception.BusinessException;
import br.com.dginx.exception.EntityNotFound;
import br.com.dginx.model.Person;
import br.com.dginx.repository.PersonRepository;
import br.com.dginx.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final MapperUtil mapperUtil;
    private final FamilyApplyService familyApplyService;

    public PersonDTO create(PersonDTO dto) {

        Optional<Person> findPerson = personRepository.findByCpf(dto.getCpf());
        if (findPerson.isPresent()) {
            throw new BusinessException("Person already exist, CPF: "+dto.getCpf());
        }
        dto.setId(UUID.randomUUID().toString());
        Person save = personRepository.save(mapperUtil.convertTo(dto, Person.class));
        return mapperUtil.convertTo(save, PersonDTO.class);
    }

    public PersonDTO update(String id, PersonDTO dto) {

        Person findPerson = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Person not found, ID: " + id));
        findPerson.setBirthDate(dto.getBirthDate());
        findPerson.setFullName(dto.getFullName());
        findPerson.setCpf(dto.getCpf());
        findPerson.setSalary(dto.getSalary());

        PersonDTO updatedPersonDTO = mapperUtil.convertTo(personRepository.save(findPerson), PersonDTO.class);
        familyApplyService.updateFamilyApplyIfFindPerson(updatedPersonDTO.getId());
        return updatedPersonDTO;
    }

    public Person getById(String id) {
        return personRepository.findById(id).orElseThrow(() -> new EntityNotFound("Person not found, ID: " + id));
    }

}
