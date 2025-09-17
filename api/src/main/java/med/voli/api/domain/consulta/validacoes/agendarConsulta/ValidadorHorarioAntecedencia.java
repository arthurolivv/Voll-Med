package med.voli.api.domain.consulta.validacoes.agendarConsulta;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.DadosAgendamentoConsultaDto;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto){

        var dataConsulta = dadosAgendamentoConsultaDto.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos!");
        }
    }
}
