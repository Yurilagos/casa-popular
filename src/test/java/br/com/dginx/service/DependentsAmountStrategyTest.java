package br.com.dginx.service;

import br.com.dginx.datamock.DataMock;
import br.com.dginx.repository.PersonRepository;
import br.com.dginx.strategy.DependentsAmountStrategy;
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
public class DependentsAmountStrategyTest {

    @InjectMocks
    private DependentsAmountStrategy dependentsAmountStrategy;
    @Mock
    private PersonRepository personRepository;

    private final DataMock dataMock = DataMock.getInstance();

    @SuppressWarnings("unchecked")
    @DisplayName("should calculate DependentsAmount three points successful")
    @Test
    void shouldCalculateDependentsAmountThreePointsSuccessful() {
        var familyApply = dataMock.getFamilyApplyPointsThreeDependentsTest();
        when(personRepository.findById(anyString())).thenReturn(
                Optional.of(dataMock.getDependent1()),
                Optional.of(dataMock.getDependent2()),
                Optional.of(dataMock.getDependent3()));

        dependentsAmountStrategy.executeStrategy(familyApply);

        assertEquals(familyApply.getPoints(), Utils.INT_3);
    }

    @SuppressWarnings("unchecked")
    @DisplayName("should calculate five salary points successful")
    @Test
    void shouldCalculateSalaryFivePointsSuccessful() {
        var familyApply = dataMock.getFamilyApplyPointsTwoDependentsTest();
        when(personRepository.findById(anyString())).thenReturn(
                Optional.of(dataMock.getDependent1()),
                Optional.of(dataMock.getDependent2()));

        dependentsAmountStrategy.executeStrategy(familyApply);

        assertEquals(familyApply.getPoints(), Utils.INT_2);
    }

}
