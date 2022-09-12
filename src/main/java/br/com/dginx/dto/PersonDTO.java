package br.com.dginx.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String id;
	@NotBlank
	private Long cpf;
	@NotBlank
	private String fullName;
	@NotBlank
	private LocalDate birthDate;
	
	private Double salary;

}
