package br.com.dginx.service;

import br.com.dginx.dto.FamilyApplyDTO;
import br.com.dginx.dto.PersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitService {

    private final PersonService personService;
    private final FamilyApplyService familyApplyService;

    public void generateData() {
        createFonsecaFamily();
        createRochaFamily();
        createVenturaFamily();
        createMarinsFamily();
    }

    private void createFonsecaFamily() {
        PersonDTO marcos_fonsecaDTO = PersonDTO.builder()
                .fullName("Marcos Fonseca")
                .birthDate(LocalDate.of(1991, 07, 22))
                .cpf(423456789L)
                .salary(500d)
                .build();

        String marcosFonsecaId = personService.create(marcos_fonsecaDTO).getId();

        PersonDTO daniele_fonsecaDTO = PersonDTO.builder()
                .fullName("Daniele Fonseca")
                .birthDate(LocalDate.of(1992, 07, 22))
                .cpf(523456719L)
                .salary(200d)
                .build();

        String danieleFonsecaId = personService.create(daniele_fonsecaDTO).getId();

        PersonDTO gabriel_fonsecaDTO = PersonDTO.builder()
                .fullName("Gabriel Fonseca")
                .birthDate(LocalDate.of(2020, 07, 22))
                .cpf(522426789L)
                .build();

        String gabrielFonsecaId = personService.create(gabriel_fonsecaDTO).getId();

        FamilyApplyDTO fonsecaFamily = FamilyApplyDTO.builder()
                .applicantId(marcosFonsecaId)
                .spouseId(danieleFonsecaId)
                .dependentsId(List.of(gabrielFonsecaId))
                .build();

        familyApplyService.create(fonsecaFamily);
    }

    private void createRochaFamily() {
        PersonDTO fernanda_rochaDTO = PersonDTO.builder()
                .fullName("Fernanda Rocha")
                .birthDate(LocalDate.of(1995, 02, 20))
                .cpf(123456799L)
                .salary(800d)
                .build();

        String fernandaRochaId = personService.create(fernanda_rochaDTO).getId();

        PersonDTO marcelo_rochaDTO = PersonDTO.builder()
                .fullName("Marcelo Rocha")
                .birthDate(LocalDate.of(1988, 01, 12))
                .cpf(123496719L)
                .salary(300d)
                .build();

        String marceloRochaId = personService.create(marcelo_rochaDTO).getId();

        PersonDTO juliana_rochaDTO = PersonDTO.builder()
                .fullName("Juliana Rocha")
                .birthDate(LocalDate.of(2021, 07, 22))
                .cpf(123426719L)
                .build();

        String julianaRochaId = personService.create(juliana_rochaDTO).getId();

        PersonDTO mario_rochaDTO = PersonDTO.builder()
                .fullName("Mario Rocha")
                .birthDate(LocalDate.of(2020, 06, 22))
                .cpf(1234268289L)
                .build();

        String marioRochaId = personService.create(mario_rochaDTO).getId();

        PersonDTO mariana_rochaDTO = PersonDTO.builder()
                .fullName("Mariana Rocha")
                .birthDate(LocalDate.of(2022, 07, 02))
                .cpf(1234266639L)
                .build();

        String marianaRochaId = personService.create(mariana_rochaDTO).getId();

        FamilyApplyDTO rochaFamily = FamilyApplyDTO.builder()
                .applicantId(fernandaRochaId)
                .spouseId(marceloRochaId)
                .dependentsId(List.of(julianaRochaId, marioRochaId, marianaRochaId))
                .build();

        familyApplyService.create(rochaFamily);
    }

    private void createVenturaFamily() {
        PersonDTO jose_venturaDTO = PersonDTO.builder()
                .fullName("José Ventura")
                .birthDate(LocalDate.of(1985, 02, 20))
                .cpf(123456791L)
                .salary(1000d)
                .build();

        String joseVenturaId = personService.create(jose_venturaDTO).getId();

        PersonDTO denise_venturaDTO = PersonDTO.builder()
                .fullName("Denise Ventura")
                .birthDate(LocalDate.of(1988, 02, 12))
                .cpf(1234961719L)
                .salary(900d)
                .build();

        String deniseVenturaId = personService.create(denise_venturaDTO).getId();

        PersonDTO carla_venturaDTO = PersonDTO.builder()
                .fullName("Carla Ventura")
                .birthDate(LocalDate.of(2021, 07, 22))
                .cpf(32342622319L)
                .build();

        String carlaVenturaId = personService.create(carla_venturaDTO).getId();

        PersonDTO joao_venturaDTO = PersonDTO.builder()
                .fullName("João Ventura")
                .birthDate(LocalDate.of(2020, 06, 22))
                .cpf(1434268289L)
                .build();

        String joaoVenturaId = personService.create(joao_venturaDTO).getId();



        FamilyApplyDTO venturaFamily = FamilyApplyDTO.builder()
                .applicantId(joseVenturaId)
                .spouseId(deniseVenturaId)
                .dependentsId(List.of(joaoVenturaId, carlaVenturaId))
                .build();

        familyApplyService.create(venturaFamily);
    }


    private void createMarinsFamily() {
        PersonDTO pedro_marinsDTO = PersonDTO.builder()
                .fullName("Pedro Marins")
                .birthDate(LocalDate.of(1989, 02, 20))
                .cpf(223456791L)
                .salary(500d)
                .build();

        String pedroMarinsId = personService.create(pedro_marinsDTO).getId();

        PersonDTO cristina_marinsDTO = PersonDTO.builder()
                .fullName("Cristina Marins")
                .birthDate(LocalDate.of(1990, 05, 12))
                .cpf(2234961719L)
                .salary(250d)
                .build();

        String cristinaMarinsId = personService.create(cristina_marinsDTO).getId();

        PersonDTO victor_marinsDTO = PersonDTO.builder()
                .fullName("Victor Marins")
                .birthDate(LocalDate.of(2020, 07, 22))
                .cpf(33342622319L)
                .build();

        String victorMarinsId = personService.create(victor_marinsDTO).getId();




        FamilyApplyDTO marinsFamily = FamilyApplyDTO.builder()
                .applicantId(pedroMarinsId)
                .spouseId(cristinaMarinsId)
                .dependentsId(List.of(victorMarinsId))
                .build();

        familyApplyService.create(marinsFamily);
    }
}
