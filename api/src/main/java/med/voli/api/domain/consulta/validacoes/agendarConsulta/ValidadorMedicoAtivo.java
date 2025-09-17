package med.voli.api.domain.consulta.validacoes.agendarConsulta;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.DadosAgendamentoConsultaDto;
import med.voli.api.domain.medicos.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto) {

    //escolha do medico e opcional
    if(dadosAgendamentoConsultaDto.idMedico() == null){
        return;
    }

    //se o medico tiver sido previamente escolhido, executa:
    var medicoEstaAtivo = medicoRepository.findAtivoById(dadosAgendamentoConsultaDto.idMedico());
    if(!medicoEstaAtivo){
        throw new ValidacaoException("A consulta não pode ser realizada com um médico excluído!");
    }

    }
    }
