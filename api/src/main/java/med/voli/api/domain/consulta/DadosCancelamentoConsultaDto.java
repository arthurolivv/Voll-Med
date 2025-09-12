package med.voli.api.domain.consulta;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

public record DadosCancelamentoConsultaDto(

        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo
) {


}
