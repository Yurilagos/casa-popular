package br.com.dginx.datamock;

import br.com.dginx.dto.FamilyApplyDTO;
import br.com.dginx.dto.PersonDTO;
import br.com.dginx.model.FamilyApply;
import br.com.dginx.model.Person;
import br.com.dginx.util.Utils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DataMock {

    private static DataMock uniqueInstance;

    private DataMock() {
    }

    public static synchronized DataMock getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new DataMock();
        }

        return uniqueInstance;
    }


    public Person getPerson() {
        return Person.builder()
                .id(UUID.randomUUID().toString())
                .fullName("Joao Silva")
                .birthDate(LocalDate.now())
                .cpf(123456789L)
                .salary(500d)
                .build();
    }

    public Person getPersonWith200Salary() {
        return Person.builder()
                .id(UUID.randomUUID().toString())
                .fullName("Joao Silva")
                .birthDate(LocalDate.now())
                .cpf(123456789L)
                .salary(200d)
                .build();
    }

    public PersonDTO getPersonDTO() {
        return PersonDTO.builder()
                .id(UUID.randomUUID().toString())
                .fullName("Joao Silva")
                .birthDate(LocalDate.now())
                .cpf(123456789L)
                .salary(500d)
                .build();
    }

    public Person getUpdatedPerson() {
        return Person.builder()
                .id(UUID.randomUUID().toString())
                .fullName("Joao da Silva")
                .birthDate(LocalDate.now())
                .cpf(123456789L)
                .salary(400d)
                .build();
    }

    public PersonDTO getUpdatedPersonDTO() {
        return PersonDTO.builder()
                .id(UUID.randomUUID().toString())
                .fullName("Joao da Silva")
                .birthDate(LocalDate.now())
                .cpf(123456789L)
                .salary(400d)
                .build();
    }

    public Person getPersonSpouse() {
        return Person.builder()
                .id(UUID.randomUUID().toString())
                .fullName("Joao Silva")
                .birthDate(LocalDate.now())
                .cpf(123456789L)
                .salary(500d)
                .build();
    }

    public PersonDTO getPersonSpouseDTO() {
        return PersonDTO.builder()
                .id(UUID.randomUUID().toString())
                .fullName("Maria Silva")
                .birthDate(LocalDate.now())
                .cpf(123456782L)
                .salary(500d)
                .build();
    }


    public FamilyApply getFamilyApply() {
        return FamilyApply.builder()
                .id(UUID.randomUUID().toString())
                .applicantId(UUID.randomUUID().toString())
                .spouseId(UUID.randomUUID().toString())
                .points(Utils.INT_3)
                .tiebreaker(500d)
                .build();
    }

    public FamilyApply getFamilyApplyPointsTest() {
        return FamilyApply.builder()
                .id(UUID.randomUUID().toString())
                .applicantId(UUID.randomUUID().toString())
                .spouseId(UUID.randomUUID().toString())
                .build();
    }

    public FamilyApply getFamilyApplyPointsTest2() {
        return FamilyApply.builder()
                .id(UUID.randomUUID().toString())
                .applicantId(UUID.randomUUID().toString())
                .spouseId(UUID.randomUUID().toString())
                .build();
    }

    public FamilyApply getFamilyApplyPointsThreeDependentsTest() {
        return FamilyApply.builder()
                .id(UUID.randomUUID().toString())
                .applicantId(UUID.randomUUID().toString())
                .spouseId(UUID.randomUUID().toString())
                .dependentsId(List.of(UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()))
                .tiebreaker(500d)
                .build();
    }


    public FamilyApply getFamilyApplyPointsTwoDependentsTest() {
        return FamilyApply.builder()
                .id(UUID.randomUUID().toString())
                .applicantId(UUID.randomUUID().toString())
                .spouseId(UUID.randomUUID().toString())
                .dependentsId(List.of(UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()))
                .tiebreaker(500d)
                .build();
    }

    public FamilyApplyDTO getFamilyApplyDTO() {
        return FamilyApplyDTO.builder()
                .id(UUID.randomUUID().toString())
                .applicantId(UUID.randomUUID().toString())
                .spouseId(UUID.randomUUID().toString())
                .points(Utils.INT_3)
                .tiebreaker(500d)
                .build();
    }

    public FamilyApplyDTO getFamilyApplyWithDependentsDTO() {
        return FamilyApplyDTO.builder()
                .id(UUID.randomUUID().toString())
                .applicantId(UUID.randomUUID().toString())
                .spouseId(UUID.randomUUID().toString())
                .dependentsId(List.of(UUID.randomUUID().toString(),
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()))
                .points(Utils.INT_3)
                .tiebreaker(500d)
                .build();
    }

    public Person getDependent1() {
        return Person.builder()
                .id(UUID.randomUUID().toString())
                .fullName("Gabriel Silva")
                .birthDate(LocalDate.now())
                .cpf(123456784L)
                .build();
    }

    public Person getDependent2() {
        return Person.builder()
                .id(UUID.randomUUID().toString())
                .fullName("Danilo Silva")
                .birthDate(LocalDate.now())
                .cpf(123456783l)
                .build();
    }

    public Person getDependent3() {
        return Person.builder()
                .id(UUID.randomUUID().toString())
                .fullName("Joana Silva")
                .birthDate(LocalDate.now())
                .cpf(123456782l)
                .build();
    }


}