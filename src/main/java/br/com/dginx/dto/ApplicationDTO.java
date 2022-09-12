package br.com.dginx.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDTO extends RepresentationModel<ApplicationDTO> {

    private String familyApplyId;
    private String applicantFullName;
    private Integer points;
    @JsonIgnore
    private Double tiebreaker;

}
