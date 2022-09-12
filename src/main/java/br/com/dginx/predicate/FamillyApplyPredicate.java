package br.com.dginx.predicate;

import br.com.dginx.model.QFamillyApply;
import com.querydsl.core.types.Predicate;

import java.util.List;

public class FamillyApplyPredicate {

    public static Predicate getFamillyContainsAnyDependents(List<String> idList) {
        return QFamillyApply.famillyApply.dependentsId.any().in(idList);
    }

    public static Predicate getFamillyApplyByPersonId(String personId) {
        QFamillyApply famillyApply = QFamillyApply.famillyApply;
        return famillyApply.applicantId.eq(personId).or(famillyApply.dependentsId.any().eq(personId))
                .or(famillyApply.spouseId.eq(personId));
    }

}
