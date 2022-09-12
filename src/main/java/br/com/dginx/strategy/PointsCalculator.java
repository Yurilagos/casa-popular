package br.com.dginx.strategy;

import br.com.dginx.model.FamilyApply;
import br.com.dginx.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PointsCalculator {

    private List<Strategy<FamilyApply>> strategyList = new ArrayList<>();

    public PointsCalculator(@Autowired PersonRepository personRepository) {
        strategyList.add(new SalaryStrategy(personRepository));
        strategyList.add(new DependentsAmountStrategy(personRepository));
    }

    public void executeAll(FamilyApply familyApply) {
        familyApply.resetPoints();
        strategyList.forEach(strategy -> strategy.executeStrategy(familyApply));
    }

    public void addStrategy(Strategy<FamilyApply> strategy) {
        strategyList.add(strategy);
    }

}
