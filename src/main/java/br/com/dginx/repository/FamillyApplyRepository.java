package br.com.dginx.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import br.com.dginx.model.FamillyApply;

public interface FamillyApplyRepository
		extends MongoRepository<FamillyApply, String>, QuerydslPredicateExecutor<FamillyApply> {

	Optional<FamillyApply> findByApplicantIdOrSpouseId(String applicantId, String SpouseId);

}
