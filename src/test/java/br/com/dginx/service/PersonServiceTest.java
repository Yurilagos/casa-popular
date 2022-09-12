package br.com.dginx.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.dginx.datamock.DataMock;
import br.com.dginx.dto.PersonDTO;
import br.com.dginx.exception.BusinessException;
import br.com.dginx.model.Person;
import br.com.dginx.repository.PersonRepository;
import br.com.dginx.util.MapperUtil;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@InjectMocks
	private PersonService personService;

	@Mock
	private PersonRepository personRepository;
	@Mock
	private FamilyApplyService familyApplyService;
	@Mock
	private MapperUtil mapperUtil;

	private final DataMock dataMock = DataMock.getInstance();

	@DisplayName("should create person with successful")
	@Test
	void shouldCreatePersonWithSuccessful() {
		var person = dataMock.getPerson();
		var personDTO = dataMock.getPersonDTO();

		when(personRepository.save(any(Person.class))).thenReturn(person);
		when(personRepository.findByCpf(any(Long.class))).thenReturn(Optional.empty());
		when(mapperUtil.convertTo(personDTO, Person.class)).thenReturn(person);
		when(mapperUtil.convertTo(person, PersonDTO.class)).thenReturn(personDTO);

		PersonDTO result = personService.create(personDTO);

		assertNotNull(result);
		assertEquals(personDTO.getFullName(), result.getFullName());
		verify(personRepository, atLeastOnce()).save(any(Person.class));
		verify(personRepository, atLeastOnce()).findByCpf(any(Long.class));

	}

	@DisplayName("should throw exception when cpf already exist")
	@Test
	void shouldThrowExceptionWhenCpfAlreadyExist() {
		var person = dataMock.getPerson();
		var personDTO = dataMock.getPersonDTO();
		BusinessException exception = assertThrows(BusinessException.class, () -> {

			when(personRepository.findByCpf(any(Long.class))).thenReturn(Optional.of(person));

			personService.create(personDTO);
		});

		assertThat(exception.getMessage()).isEqualToIgnoringCase("Person already exist, CPF: "+personDTO.getCpf());
		verify(personRepository, atLeastOnce()).findByCpf(any(Long.class));
	}

	@DisplayName("should update person with successful")
	@Test
	void shouldUpdatePersonWithSuccessful() {
		var person = dataMock.getPerson();
		var personDTO = dataMock.getPersonDTO();
		var updatedPerson = dataMock.getUpdatedPerson();
		var updatedPersonDTO = dataMock.getUpdatedPersonDTO();

		when(personRepository.findById(anyString())).thenReturn(Optional.of(person));
		when(personRepository.save(any(Person.class))).thenReturn(updatedPerson);
		when(mapperUtil.convertTo(updatedPerson, PersonDTO.class)).thenReturn(updatedPersonDTO);
		doNothing().when(familyApplyService).updateFamilyApplyIfFindPerson(anyString());

		PersonDTO result = personService.update(personDTO.getId(), updatedPersonDTO);

		assertNotNull(result);
		assertEquals(updatedPersonDTO.getFullName(), result.getFullName());
		assertEquals(updatedPersonDTO.getSalary(), result.getSalary());
		verify(personRepository, atLeastOnce()).save(any(Person.class));
		verify(personRepository, atLeastOnce()).findById(anyString());

	}

}
