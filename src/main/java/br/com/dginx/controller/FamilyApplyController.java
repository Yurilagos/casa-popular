package br.com.dginx.controller;

import br.com.dginx.assembler.FamilyApplyAssembler;
import br.com.dginx.controller.spec.FamilyApplyControllerSpec;
import br.com.dginx.dto.ApplicationDTO;
import br.com.dginx.dto.FamilyApplyDTO;
import br.com.dginx.model.FamilyApply;
import br.com.dginx.service.FamilyApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/familyApply")
public class FamilyApplyController implements FamilyApplyControllerSpec {

    private final FamilyApplyService familyApplyService;
    private final PagedResourcesAssembler<FamilyApply> pagedResourcesAssembler;
    private final FamilyApplyAssembler familyApplyAssembler;

    @Override
    @GetMapping
    public ResponseEntity<PagedModel<ApplicationDTO>> getAll(
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "points", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "tiebreaker", direction = Sort.Direction.ASC)}) Pageable page) {
//		log.info(new EventLogDTO(EventsEnum.GET_ALL_RANK, "").toString());
        var familyApplyPage = familyApplyService.getAll(page);
        var meditationsPageDTO = pagedResourcesAssembler.toModel(familyApplyPage, familyApplyAssembler);

        return new ResponseEntity<>(meditationsPageDTO, HttpStatus.OK);
    }

    public ResponseEntity<CollectionModel<ApplicationDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(familyApplyAssembler.toCollectionModel(familyApplyService.findAll()));
    }

    @Override
    @PostMapping()
    public ResponseEntity<FamilyApplyDTO> create(FamilyApplyDTO personDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(familyApplyService.create(personDTO));
    }

    @Override
    @PatchMapping("/calculate/{id}")
    public ResponseEntity<FamilyApplyDTO> calculatePoints(String id) {
        return ResponseEntity.status(HttpStatus.OK).body(familyApplyService.calculate(id));
    }

}
