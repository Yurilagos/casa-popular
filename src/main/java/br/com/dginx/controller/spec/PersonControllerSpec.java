package br.com.dginx.controller.spec;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.dginx.dto.PersonDTO;
import br.com.dginx.exception.ErrorMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Person Spec", tags = "Person")
public interface PersonControllerSpec {

	@ApiOperation(value = "create Person ", nickname = "Person", response = ErrorMessage.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Unexpected Error") })
	ResponseEntity<PersonDTO> create(@RequestBody PersonDTO personDTO);

	@ApiOperation(value = "update a Person ", nickname = "update")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Unexpected Error", response = ErrorMessage.class) })
	ResponseEntity<PersonDTO> update(@PathVariable String personId, @RequestBody PersonDTO personDTO);

	// TODO CRUD

}
