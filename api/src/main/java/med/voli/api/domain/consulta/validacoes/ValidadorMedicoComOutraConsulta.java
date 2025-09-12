package med.voli.api.domain.consulta.validacoes;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.ConsultaRepository;
import med.voli.api.domain.consulta.DadosAgendamentoConsultaDto;
import med.voli.api.domain.medicos.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsulta implements  ValidadorAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto) {

        var medicoComOutraConsulta = consultaRepository.existsByMedicoIdAndData(dadosAgendamentoConsultaDto.idMedico(), dadosAgendamentoConsultaDto.data());
        if(medicoComOutraConsulta){
            throw new ValidacaoException("O medico já possui uma consulta nesse horário!");
        }
    }
}
