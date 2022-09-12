package br.com.dginx.controller.spec;

import br.com.dginx.exception.ErrorMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

@Api(value = "Init Spec", tags = "Init")
public interface InitControllerSpec {

    @ApiOperation(value = "init data ", nickname = "init", response = ErrorMessage.class)
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Unexpected Error")})
    ResponseEntity<String> init();
}
