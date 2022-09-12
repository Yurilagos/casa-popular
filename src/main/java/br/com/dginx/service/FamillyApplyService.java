package br.com.dginx.service;

import br.com.dginx.dto.FamillyApplyDTO;
import br.com.dginx.exception.BusinessException;
import br.com.dginx.exception.EntityNotFound;
import br.com.dginx.model.FamillyApply;
import br.com.dginx.predicate.FamillyApplyPredicate;
import br.com.dginx.repository.FamillyApplyRepository;
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
public class FamillyApplyService {

    private final FamillyApplyRepository famillyApplyRepository;
    private final MapperUtil mapperUtil;
    private final PointsCalculator pointsCalculator;

    public FamillyApplyDTO create(FamillyApplyDTO dto) {

        validate(dto);

        dto.setId(UUID.randomUUID().toString());
        FamillyApply entity = mapperUtil.convertTo(dto, FamillyApply.class);
        pointsCalculator.executeAll(entity);
        return mapperUtil.convertTo(famillyApplyRepository.save(entity), FamillyApplyDTO.class);
    }

    private void validate(FamillyApplyDTO dto) {
        Optional<FamillyApply> findFamilly = famillyApplyRepository.findByApplicantIdOrSpouseId(dto.getApplicantId(),
                dto.getApplicantId());

        if (findFamilly.isPresent()) {
            throw new BusinessException("Familly Apply already exist.");
        }

        if (CollectionUtils.isNotEmpty(dto.getDependentsId())) {
            boolean existsFamillyApply = famillyApplyRepository
                    .exists(FamillyApplyPredicate.getFamillyContainsAnyDependents(dto.getDependentsId()));
            if (existsFamillyApply) {
                throw new BusinessException("Dependents Apply already exist.");
            }
        }
    }

    public void updateFamillyApplyIfFindPerson(String id) {
        Iterable<FamillyApply> iterableFamillyAplly = famillyApplyRepository
                .findAll(FamillyApplyPredicate.getFamillyApplyByPersonId(id));
        if (!IterableUtils.isEmpty(iterableFamillyAplly)) {
            pointsCalculator.executeAll(iterableFamillyAplly.iterator().next());
        }
    }

    public FamillyApplyDTO calculate(String id) {

        FamillyApply findFamilly = famillyApplyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Familly Apply not found."));

        pointsCalculator.executeAll(findFamilly);
        return mapperUtil.convertTo(famillyApplyRepository.save(findFamilly), FamillyApplyDTO.class);
    }

    public Page<FamillyApply> getAll(Pageable page) {
        return famillyApplyRepository.findAll(page);
    }

    public List<FamillyApply> findAll() {
        return famillyApplyRepository.findAll();
    }


}
