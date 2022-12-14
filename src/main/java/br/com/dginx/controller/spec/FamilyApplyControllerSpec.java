package br.com.dginx.controller.spec;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.dginx.dto.ApplicationDTO;
import br.com.dginx.dto.FamilyApplyDTO;
import br.com.dginx.exception.ErrorMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "FamilyApply Spec", tags = "FamilyApply")
public interface FamilyApplyControllerSpec {

	@ApiOperation(value = "Get all FamilyApply", nickname = "getAll")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Unexpected Error", response = ErrorMessage.class) })
	ResponseEntity<PagedModel<ApplicationDTO>> getAll(Pageable page);

	@ApiOperation(value = "Get Movie ", nickname = "Get Movie", response = ErrorMessage.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Unexpected Error") })
	ResponseEntity<FamilyApplyDTO> create(@RequestBody FamilyApplyDTO dto);

	@ApiOperation(value = "Calculate points", nickname = "calculate", response = ErrorMessage.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Unexpected Error") })
	ResponseEntity<FamilyApplyDTO> calculatePoints(@PathVariable String id);

}
