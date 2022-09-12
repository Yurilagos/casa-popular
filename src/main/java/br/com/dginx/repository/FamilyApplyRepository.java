package br.com.dginx.repository;

import br.com.dginx.model.FamilyApply;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface FamilyApplyRepository
        extends MongoRepository<FamilyApply, String>, QuerydslPredicateExecutor<FamilyApply> {

    Optional<FamilyApply> findByApplicantIdOrSpouseId(String applicantId, String SpouseId);

}
