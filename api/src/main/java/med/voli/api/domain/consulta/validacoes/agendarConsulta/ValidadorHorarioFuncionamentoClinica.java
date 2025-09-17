package med.voli.api.domain.consulta.validacoes.agendarConsulta;


import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.DadosAgendamentoConsultaDto;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto){

        var dataConsulta = dadosAgendamentoConsultaDto.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY); //devolve boolean
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7; //boolean
        var depoisDoEncerramentoDaClinica = dataConsulta.getDayOfMonth() > 18; //boolean

        if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica){
            throw new ValidacaoException("Consulta fora do horário de atendimento da clínica!");
        }
    }
}
