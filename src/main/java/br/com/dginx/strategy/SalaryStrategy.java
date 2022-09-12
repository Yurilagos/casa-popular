package br.com.dginx.strategy;

import br.com.dginx.exception.EntityNotFound;
import br.com.dginx.model.FamillyApply;
import br.com.dginx.model.Person;
import br.com.dginx.repository.PersonRepository;
import br.com.dginx.util.Utils;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SalaryStrategy implements Strategy<FamillyApply> {

    private PersonRepository personRepository;

    @Override
    public void executeStrategy(FamillyApply famillyApply) {
        double salarySum = fillTotalSalarySum(famillyApply);
        setTieBreak(famillyApply, salarySum);
        if (salarySum <= Utils.INT_900) {
            famillyApply.addPoints(Utils.INT_5);
        } else if (salarySum <= Utils.INT_1500) {
            famillyApply.addPoints(Utils.INT_3);
        }
    }


    private double fillTotalSalarySum(FamillyApply famillyApply) {
        Person applicantPerson = personRepository.findById(famillyApply.getApplicantId())
                .orElseThrow(() -> new EntityNotFound("Person not found, ID: " + famillyApply.getApplicantId()));
        Person spousePerson = personRepository.findById(famillyApply.getSpouseId())
                .orElseThrow(() -> new EntityNotFound("Person not found, ID: " + famillyApply.getSpouseId()));

        List<Person> dependents = new ArrayList<>();
        famillyApply.getDependentsId().forEach(dependentId -> dependents.add(personRepository.findById(dependentId)
                .orElseThrow(() -> new EntityNotFound("Person not found, ID: " + famillyApply.getSpouseId()))));

        return calculateSalaryPoints(applicantPerson, spousePerson, dependents);
    }

    private double calculateSalaryPoints(Person applicantPerson, Person spousePerson,
                                         List<Person> dependents) {
        double applycantSalary = applicantPerson.getSalary();
        double spouseSalary = spousePerson.getSalary();
        double dependentsSalarySum = dependents.stream().mapToDouble(Person::getSalary).sum();

        return applycantSalary + spouseSalary + dependentsSalarySum;
    }

    private void setTieBreak(FamillyApply famillyApply, Double salarySum) {
        famillyApply.setTiebreaker(salarySum / (Utils.INT_2 + famillyApply.getDependentsId().size()));
    }


}
