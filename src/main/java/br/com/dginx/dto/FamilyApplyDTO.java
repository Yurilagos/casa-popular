package br.com.dginx.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class FamilyApplyDTO extends RepresentationModel<ApplicationDTO>{
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String id;
	@NotBlank
	private String applicantId;
	@NotBlank
	private String spouseId;
	@NotBlank
	private List<String> dependentsId;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer points;
	@JsonIgnore
	private Double tiebreaker;

}
