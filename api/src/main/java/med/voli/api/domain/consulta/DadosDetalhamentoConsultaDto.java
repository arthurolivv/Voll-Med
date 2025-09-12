package med.voli.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsultaDto(

        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data) {
}
