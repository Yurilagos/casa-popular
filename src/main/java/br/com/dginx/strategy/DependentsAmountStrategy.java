package br.com.dginx.strategy;

import br.com.dginx.exception.EntityNotFound;
import br.com.dginx.model.FamilyApply;
import br.com.dginx.model.Person;
import br.com.dginx.repository.PersonRepository;
import br.com.dginx.util.Utils;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class DependentsAmountStrategy implements Strategy<FamilyApply> {

    private PersonRepository personRepository;

    @Override
    public void executeStrategy(FamilyApply familyApply) {
        Integer amountDependents = getAmountDependentsUnder18YearsOld(familyApply);
        if (amountDependents >= Utils.INT_3) {
            familyApply.addPoints(Utils.INT_3);
        } else if (amountDependents >= Utils.INT_1) {
            familyApply.addPoints(Utils.INT_2);
        }
    }

    private Integer getAmountDependentsUnder18YearsOld(FamilyApply famillyApply) {
        return famillyApply.getDependentsId().stream().filter(dependentId -> {
            Person person = personRepository.findById(dependentId)
                    .orElseThrow(() -> new EntityNotFound("Dependet not found, ID: " + dependentId));
            return Utils.calculateAge(person.getBirthDate(), LocalDate.now()) < 18;
        }).map(e -> 1).reduce(0, Integer::sum);
    }

}
