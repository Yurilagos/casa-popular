package br.com.dginx.exception;

import br.com.dginx.enumerator.ErrorENUM;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    private String link;
    private HttpStatus status;

    private List<String> errors = new ArrayList<>();
    private List<ErrorMessage> problemObjects = new ArrayList<>();


    public ErrorMessage(final String name, final String message, final ErrorENUM errorId) {
        this.message = message;
    }


    public ErrorMessage(HttpStatus status, ErrorENUM problemType, String detail) {
        this.status = status;
        this.message = detail;
    }

    public ErrorMessage(HttpStatus status, ErrorENUM problemType, String detail, List<ErrorMessage> problemObjects) {
        this.status = status;
        this.message = detail;
        this.problemObjects = problemObjects;
    }


}