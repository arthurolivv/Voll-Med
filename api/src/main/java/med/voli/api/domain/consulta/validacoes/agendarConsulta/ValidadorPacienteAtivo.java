package med.voli.api.domain.consulta.validacoes.agendarConsulta;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.DadosAgendamentoConsultaDto;
import med.voli.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voli.api.domain.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto) {

    var pacienteEstaAtivo = pacienteRepository.findAtivoById(dadosAgendamentoConsultaDto.idPaciente());
    if(!pacienteEstaAtivo){
        throw new ValidacaoException("A consulta não pode ser realizada com um paciente excluído!");
    }

    }
    }
