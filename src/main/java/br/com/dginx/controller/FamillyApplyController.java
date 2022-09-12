package br.com.dginx.controller;

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

import br.com.dginx.assembler.FamillyApllyAssembler;
import br.com.dginx.controller.spec.FamillyApplyControllerSpec;
import br.com.dginx.dto.ApplicationDTO;
import br.com.dginx.dto.FamillyApplyDTO;
import br.com.dginx.model.FamillyApply;
import br.com.dginx.service.FamillyApplyService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/famillyApply")
public class FamillyApplyController implements FamillyApplyControllerSpec {

	private final FamillyApplyService famillyApplyService;
	private final PagedResourcesAssembler<FamillyApply> pagedResourcesAssembler;
	private final FamillyApllyAssembler famillyApllyAssembler;

	@Override
	@GetMapping
	public ResponseEntity<PagedModel<ApplicationDTO>> getAll(
			@SortDefault.SortDefaults({
				@SortDefault(sort = "points", direction = Sort.Direction.DESC), 
				@SortDefault(sort = "tiebreaker", direction = Sort.Direction.ASC)}) Pageable page) {
//		log.info(new EventLogDTO(EventsEnum.GET_ALL_RANK, "").toString());
		var famillyApplyPage = famillyApplyService.getAll(page);
		var meditationsPageDTO = pagedResourcesAssembler.toModel(famillyApplyPage, famillyApllyAssembler);

		return new ResponseEntity<>(meditationsPageDTO, HttpStatus.OK);
	}

	public ResponseEntity<CollectionModel<ApplicationDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(famillyApllyAssembler.toCollectionModel(famillyApplyService.findAll()));
	}

	@Override
	@PostMapping()
	public ResponseEntity<FamillyApplyDTO> create(FamillyApplyDTO personDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(famillyApplyService.create(personDTO));
	}

	@Override
	@PatchMapping("/calculate/{id}")
	public ResponseEntity<FamillyApplyDTO> calculatePoints(String id) {
		return ResponseEntity.status(HttpStatus.OK).body(famillyApplyService.calculate(id));
	}

}
