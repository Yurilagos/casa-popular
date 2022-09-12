package br.com.dginx.service;

import br.com.dginx.datamock.DataMock;
import br.com.dginx.dto.FamillyApplyDTO;
import br.com.dginx.exception.BusinessException;
import br.com.dginx.model.FamillyApply;
import br.com.dginx.repository.FamillyApplyRepository;
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
public class FamillyApplyServiceTest {

    @InjectMocks
    private FamillyApplyService famillyApplyService;

    @Mock
    private FamillyApplyRepository famillyApplyRepository;
    @Mock
    private MapperUtil mapperUtil;
    @Mock
    private PointsCalculator pointsCalculator;

    private final DataMock dataMock = DataMock.getInstance();

  
    @DisplayName("should create FamillyApply with successful")
    @Test
    void shouldCreateFamillyApplyWithSuccessful() {
        var famillyApply = dataMock.getFamillyApply();
        var famillyApplyDTO = dataMock.getFamillyApplyDTO();

        when(famillyApplyRepository.findByApplicantIdOrSpouseId(anyString(), anyString())).thenReturn(Optional.empty());
        when(mapperUtil.convertTo(famillyApplyDTO, FamillyApply.class)).thenReturn(famillyApply);
        doNothing().when(pointsCalculator).executeAll(any(FamillyApply.class));
        when(famillyApplyRepository.save(any(FamillyApply.class))).thenReturn(famillyApply);
        when(mapperUtil.convertTo(famillyApply, FamillyApplyDTO.class)).thenReturn(famillyApplyDTO);

        FamillyApplyDTO result = famillyApplyService.create(famillyApplyDTO);

        assertNotNull(result);
        verify(famillyApplyRepository, atLeastOnce()).findByApplicantIdOrSpouseId(anyString(), anyString());
        verify(famillyApplyRepository, atLeastOnce()).save(any(FamillyApply.class));

    }

    @DisplayName("should throw exception when Familly Apply already exist")
    @Test
    void shouldThrowExceptionWhenFamillyApplyAlreadyExist() {
        var famillyApply = dataMock.getFamillyApply();
        var famillyApplyDTO = dataMock.getFamillyApplyDTO();

        BusinessException exception = assertThrows(BusinessException.class, () -> {

            when(famillyApplyRepository.findByApplicantIdOrSpouseId(anyString(), anyString()))
                    .thenReturn(Optional.of(famillyApply));

            famillyApplyService.create(famillyApplyDTO);
        });
        assertThat(exception.getMessage()).isEqualToIgnoringCase("Familly Apply already exist.");
    }

    @DisplayName("should throw exception when dependents already exist in other application")
    @Test
    void shouldThrowExceptionWhenDependnetsAlreadyExistInOtherApplication() {
        var famillyApplyDTO = dataMock.getFamillyApplyWithDependentsDTO();

        BusinessException exception = assertThrows(BusinessException.class, () -> {

            when(famillyApplyRepository.exists(any(Predicate.class))).thenReturn(true);

            famillyApplyService.create(famillyApplyDTO);
        });
        assertThat(exception.getMessage()).isEqualToIgnoringCase("Dependents Apply already exist.");
    }
}
