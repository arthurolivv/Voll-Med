package med.voli.api.domain.consulta.validacoes.agendarConsulta;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.ConsultaRepository;
import med.voli.api.domain.consulta.DadosAgendamentoConsultaDto;
import med.voli.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //componente generico
public class ValidadorPacienteSemOutraConsulta implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto) {

        var primeiroHorario = dadosAgendamentoConsultaDto.data().withHour(7);
        var ultimoHorario = dadosAgendamentoConsultaDto.data().withHour(18);
        var pacienteSemOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dadosAgendamentoConsultaDto.idPaciente(), primeiroHorario, ultimoHorario);
        if(pacienteSemOutraConsultaNoDia){
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia!");
        }
    }
}
