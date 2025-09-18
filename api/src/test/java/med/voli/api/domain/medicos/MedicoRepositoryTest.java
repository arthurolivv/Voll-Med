package med.voli.api.domain.medicos;


import med.voli.api.domain.consulta.Consulta;
import med.voli.api.domain.endereco.EnderecoDto;
import med.voli.api.domain.pacientes.Paciente;
import med.voli.api.domain.pacientes.PacienteDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import java.time.temporal.TemporalAdjusters;

import static med.voli.api.domain.endereco.Uf.SP;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest //testar alguma coisa da camada de persistencia spring data JPA/ repository
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //altera para testar com bd de verdade e nao em memoria principal
@ActiveProfiles("test") //le o apllication properties test
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver NULL quando medico cadastrado nao esta disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {

        var proximaSegundaAsDez = LocalDateTime.now()
                .with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
                .toLocalDate().atTime(10, 0); //altera pra proxima segunda feira a partir da data atual as 10h

        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especiality.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAsDez);

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especiality.ORTOPEDIA, proximaSegundaAsDez);
        assertThat(medicoLivre).isNull();
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data, null));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especiality especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private MedicoDto dadosMedico(String nome, String email, String crm, Especiality especialidade) {
        return new MedicoDto(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private PacienteDto dadosPaciente(String nome, String email, String cpf) {
        return new PacienteDto(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private EnderecoDto dadosEndereco() {
        return new EnderecoDto(
                "rua xpto",
                23233L,
                "00000000",
                "Bairro",
                "Brasilia",
                SP,
                "08765444"
        );
    }

}