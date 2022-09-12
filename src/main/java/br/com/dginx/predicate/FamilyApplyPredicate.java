package br.com.dginx.predicate;

import br.com.dginx.model.QFamilyApply;
import com.querydsl.core.types.Predicate;

import java.util.List;

public class FamilyApplyPredicate {

    public static Predicate getFamilyContainsAnyDependents(List<String> idList) {
        return QFamilyApply.familyApply.dependentsId.any().in(idList);
    }

    public static Predicate getFamilyApplyByPersonId(String personId) {
        QFamilyApply familyApply = QFamilyApply.familyApply;
        return familyApply.applicantId.eq(personId).or(familyApply.dependentsId.any().eq(personId)).or(familyApply.spouseId.eq(personId));

    }

}
