package br.com.dginx.strategy;

import br.com.dginx.exception.EntityNotFound;
import br.com.dginx.model.FamilyApply;
import br.com.dginx.model.Person;
import br.com.dginx.repository.PersonRepository;
import br.com.dginx.util.Utils;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SalaryStrategy implements Strategy<FamilyApply> {

    private PersonRepository personRepository;

    @Override
    public void executeStrategy(FamilyApply familyApply) {
        double salarySum = fillTotalSalarySum(familyApply);
        setTieBreak(familyApply, salarySum);
        if (salarySum <= Utils.INT_900) {
            familyApply.addPoints(Utils.INT_5);
        } else if (salarySum <= Utils.INT_1500) {
            familyApply.addPoints(Utils.INT_3);
        }
    }


    private double fillTotalSalarySum(FamilyApply familyApply) {
        Person applicantPerson = personRepository.findById(familyApply.getApplicantId())
                .orElseThrow(() -> new EntityNotFound("Person not found, ID: " + familyApply.getApplicantId()));
        Person spousePerson = personRepository.findById(familyApply.getSpouseId())
                .orElseThrow(() -> new EntityNotFound("Person not found, ID: " + familyApply.getSpouseId()));

        List<Person> dependents = new ArrayList<>();
        familyApply.getDependentsId().forEach(dependentId -> dependents.add(personRepository.findById(dependentId)
                .orElseThrow(() -> new EntityNotFound("Person not found, ID: " + familyApply.getSpouseId()))));

        return calculateSalaryPoints(applicantPerson, spousePerson, dependents);
    }

    private double calculateSalaryPoints(Person applicantPerson, Person spousePerson,
                                         List<Person> dependents) {
        double applycantSalary = applicantPerson.getSalary();
        double spouseSalary = spousePerson.getSalary();
        double dependentsSalarySum = dependents.stream().mapToDouble(Person::getSalary).sum();

        return applycantSalary + spouseSalary + dependentsSalarySum;
    }

    private void setTieBreak(FamilyApply familyApply, Double salarySum) {
        familyApply.setTiebreaker(salarySum / (Utils.INT_2 + familyApply.getDependentsId().size()));
    }


}
