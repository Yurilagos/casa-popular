package br.com.dginx.service;

import br.com.dginx.datamock.DataMock;
import br.com.dginx.dto.FamilyApplyDTO;
import br.com.dginx.exception.BusinessException;
import br.com.dginx.model.FamilyApply;
import br.com.dginx.repository.FamilyApplyRepository;
import br.com.dginx.strategy.PointsCalculator;
import br.com.dginx.util.MapperUtil;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FamilyApplyServiceTest {

    @InjectMocks
    private FamilyApplyService familyApplyService;

    @Mock
    private FamilyApplyRepository familyApplyRepository;
    @Mock
    private MapperUtil mapperUtil;
    @Mock
    private PointsCalculator pointsCalculator;

    private final DataMock dataMock = DataMock.getInstance();


    @DisplayName("should create FamilyApply with successful")
    @Test
    void shouldCreateFamilyApplyWithSuccessful() {
        var familyApply = dataMock.getFamilyApply();
        var familyApplyDTO = dataMock.getFamilyApplyDTO();

        when(familyApplyRepository.findByApplicantIdOrSpouseId(anyString(), anyString())).thenReturn(Optional.empty());
        when(mapperUtil.convertTo(familyApplyDTO, FamilyApply.class)).thenReturn(familyApply);
        doNothing().when(pointsCalculator).executeAll(any(FamilyApply.class));
        when(familyApplyRepository.save(any(FamilyApply.class))).thenReturn(familyApply);
        when(mapperUtil.convertTo(familyApply, FamilyApplyDTO.class)).thenReturn(familyApplyDTO);

        FamilyApplyDTO result = familyApplyService.create(familyApplyDTO);

        assertNotNull(result);
        verify(familyApplyRepository, atLeastOnce()).findByApplicantIdOrSpouseId(anyString(), anyString());
        verify(familyApplyRepository, atLeastOnce()).save(any(FamilyApply.class));

    }

    @DisplayName("should throw exception when Family Apply already exist")
    @Test
    void shouldThrowExceptionWhenFamilyApplyAlreadyExist() {
        var familyApply = dataMock.getFamilyApply();
        var familyApplyDTO = dataMock.getFamilyApplyDTO();

        BusinessException exception = assertThrows(BusinessException.class, () -> {

            when(familyApplyRepository.findByApplicantIdOrSpouseId(anyString(), anyString()))
                    .thenReturn(Optional.of(familyApply));

            familyApplyService.create(familyApplyDTO);
        });
        assertThat(exception.getMessage()).isEqualToIgnoringCase("Family Apply already exist.");
    }

    @DisplayName("should throw exception when dependents already exist in other application")
    @Test
    void shouldThrowExceptionWhenDependnetsAlreadyExistInOtherApplication() {
        var familyApplyDTO = dataMock.getFamilyApplyWithDependentsDTO();

        BusinessException exception = assertThrows(BusinessException.class, () -> {

            when(familyApplyRepository.exists(any(Predicate.class))).thenReturn(true);

            familyApplyService.create(familyApplyDTO);
        });
        assertThat(exception.getMessage()).isEqualToIgnoringCase("Dependents Apply already exist.");
    }
}
