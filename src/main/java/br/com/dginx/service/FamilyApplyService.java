package br.com.dginx.service;

import br.com.dginx.dto.FamilyApplyDTO;
import br.com.dginx.exception.BusinessException;
import br.com.dginx.exception.EntityNotFound;
import br.com.dginx.model.FamilyApply;
import br.com.dginx.predicate.FamilyApplyPredicate;
import br.com.dginx.repository.FamilyApplyRepository;
import br.com.dginx.strategy.PointsCalculator;
import br.com.dginx.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FamilyApplyService {

    private final FamilyApplyRepository familyApplyRepository;
    private final MapperUtil mapperUtil;
    private final PointsCalculator pointsCalculator;

    public FamilyApplyDTO create(FamilyApplyDTO dto) {

        validate(dto);

        dto.setId(UUID.randomUUID().toString());
        FamilyApply entity = mapperUtil.convertTo(dto, FamilyApply.class);
        pointsCalculator.executeAll(entity);
        return mapperUtil.convertTo(familyApplyRepository.save(entity), FamilyApplyDTO.class);
    }

    private void validate(FamilyApplyDTO dto) {
        Optional<FamilyApply> findFamily = familyApplyRepository.findByApplicantIdOrSpouseId(dto.getApplicantId(),
                dto.getApplicantId());

        if (findFamily.isPresent()) {
            throw new BusinessException("Family Apply already exist.");
        }

        if (CollectionUtils.isNotEmpty(dto.getDependentsId())) {
            boolean existsFamilyApply = familyApplyRepository
                    .exists(FamilyApplyPredicate.getFamilyContainsAnyDependents(dto.getDependentsId()));
            if (existsFamilyApply) {
                throw new BusinessException("Dependents Apply already exist.");
            }
        }
    }

    public void updateFamilyApplyIfFindPerson(String id) {
        Iterable<FamilyApply> iterableFamilyApply = familyApplyRepository
                .findAll(FamilyApplyPredicate.getFamilyApplyByPersonId(id));
        if (!IterableUtils.isEmpty(iterableFamilyApply)) {
            pointsCalculator.executeAll(iterableFamilyApply.iterator().next());
        }
    }

    public FamilyApplyDTO calculate(String id) {

        FamilyApply findFamily = familyApplyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Family Apply not found."));

        pointsCalculator.executeAll(findFamily);
        return mapperUtil.convertTo(familyApplyRepository.save(findFamily), FamilyApplyDTO.class);
    }

    public Page<FamilyApply> getAll(Pageable page) {
        return familyApplyRepository.findAll(page);
    }

    public List<FamilyApply> findAll() {
        return familyApplyRepository.findAll();
    }


}
