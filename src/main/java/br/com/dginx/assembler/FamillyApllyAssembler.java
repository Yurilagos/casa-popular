package br.com.dginx.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.dginx.controller.FamillyApplyController;
import br.com.dginx.dto.ApplicationDTO;
import br.com.dginx.model.FamillyApply;
import br.com.dginx.service.PersonService;

@Component
public class FamillyApllyAssembler extends RepresentationModelAssemblerSupport<FamillyApply, ApplicationDTO> {

	@Autowired
	private PersonService personService;

	public FamillyApllyAssembler() {
		super(FamillyApplyController.class, ApplicationDTO.class);
	}

	@Override
	public ApplicationDTO toModel(FamillyApply entity) {
		ApplicationDTO applicationDTO = instantiateModel(entity);

		applicationDTO.setFamillyApplyId(entity.getId());
		applicationDTO.setApplycantFullName(personService.getById(entity.getApplicantId()).getFullName());
		applicationDTO.setPoints(entity.getPoints());
		applicationDTO.setTiebreaker(entity.getTiebreaker());
		return applicationDTO;
	}

}
