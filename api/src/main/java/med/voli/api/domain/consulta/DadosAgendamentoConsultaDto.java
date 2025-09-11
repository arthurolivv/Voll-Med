package med.voli.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsultaDto(

        @NotNull
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        @JsonAlias({"data_consulta", "data_da_consulta"}) //apelido para usar o Json
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm") //formtar o horario
        LocalDateTime data
) {
}
