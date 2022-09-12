package br.com.dginx.controller;

import br.com.dginx.controller.spec.PersonControllerSpec;
import br.com.dginx.dto.PersonDTO;
import br.com.dginx.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController implements PersonControllerSpec {

    private final PersonService personService;

    @PostMapping()
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO personDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(personDTO));
    }

    @Override
    @PutMapping("/{personId}")
    public ResponseEntity<PersonDTO> update(String personId, PersonDTO personDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.update(personId, personDTO));
    }

}
