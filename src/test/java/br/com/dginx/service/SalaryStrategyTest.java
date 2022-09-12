package br.com.dginx.service;

import br.com.dginx.datamock.DataMock;
import br.com.dginx.repository.PersonRepository;
import br.com.dginx.strategy.SalaryStrategy;
import br.com.dginx.util.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SalaryStrategyTest {

    @InjectMocks
    private SalaryStrategy salaryStrategy;
    @Mock
    private PersonRepository personRepository;

    private final DataMock dataMock = DataMock.getInstance();

    @SuppressWarnings("unchecked")
    @DisplayName("should calculate three salary points successful")
    @Test
    void shouldCalculateSalaryThreePointsSuccessful() {
        var famillyApply = dataMock.getFamillyApplyPointsTest();
        var applicant = dataMock.getPerson();
        var spouse = dataMock.getPersonSpouse();
        when(personRepository.findById(anyString())).thenReturn(Optional.of(applicant), Optional.of(spouse));

        salaryStrategy.executeStrategy(famillyApply);

        assertEquals(famillyApply.getPoints(), Utils.INT_3);
        assertEquals(famillyApply.getTiebreaker(), (applicant.getSalary() + spouse.getSalary())/2);
    }

    @SuppressWarnings("unchecked")
    @DisplayName("should calculate five salary points successful")
    @Test
    void shouldCalculateSalaryFivePointsSuccessful() {
        var famillyApply = dataMock.getFamillyApplyPointsTest2();
        var applicant = dataMock.getPersonWith200Salary();
        var spouse = dataMock.getPersonSpouse();
        when(personRepository.findById(anyString())).thenReturn(Optional.of(applicant), Optional.of(spouse));

        salaryStrategy.executeStrategy(famillyApply);

        assertEquals(famillyApply.getPoints(), Utils.INT_5);
        assertEquals(famillyApply.getTiebreaker(), (applicant.getSalary() + spouse.getSalary())/2);
    }

}
