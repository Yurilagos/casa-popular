package br.com.dginx.assembler;

import br.com.dginx.controller.FamilyApplyController;
import br.com.dginx.dto.ApplicationDTO;
import br.com.dginx.model.FamilyApply;
import br.com.dginx.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FamilyApplyAssembler extends RepresentationModelAssemblerSupport<FamilyApply, ApplicationDTO> {

    @Autowired
    private PersonService personService;

    public FamilyApplyAssembler() {
        super(FamilyApplyController.class, ApplicationDTO.class);
    }

    @Override
    public ApplicationDTO toModel(FamilyApply entity) {
        ApplicationDTO applicationDTO = instantiateModel(entity);

        applicationDTO.setFamilyApplyId(entity.getId());
        applicationDTO.setApplicantFullName(personService.getById(entity.getApplicantId()).getFullName());
        applicationDTO.setPoints(entity.getPoints());
        applicationDTO.setTiebreaker(entity.getTiebreaker());
        return applicationDTO;
    }

}
