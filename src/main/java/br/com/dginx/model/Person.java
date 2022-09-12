package br.com.dginx.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@Document("person")
@NoArgsConstructor
@ToString
public class Person {
	@Id
	private String id;

	private Long cpf;
	private LocalDate birthDate;
	private Double salary;
	private String fullName;

}
