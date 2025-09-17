package med.voli.api.domain.consulta.validacoes.agendarConsulta;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.ConsultaRepository;
import med.voli.api.domain.consulta.DadosAgendamentoConsultaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsulta implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto) {

        var medicoComOutraConsulta = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dadosAgendamentoConsultaDto.idMedico(), dadosAgendamentoConsultaDto.data());
        if(medicoComOutraConsulta){
            throw new ValidacaoException("O medico já possui uma consulta nesse horário!");
        }
    }
}
