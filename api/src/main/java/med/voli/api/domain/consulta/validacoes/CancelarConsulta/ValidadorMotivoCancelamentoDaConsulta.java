package med.voli.api.domain.consulta.validacoes.CancelarConsulta;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.DadosAgendamentoConsultaDto;
import med.voli.api.domain.consulta.DadosCancelamentoConsultaDto;
import med.voli.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voli.api.domain.consulta.validacoes.ValidadorCancelamentoDeConsulta;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMotivoCancelamentoDaConsulta implements ValidadorCancelamentoDeConsulta {

    @Override
    public void validar(DadosCancelamentoConsultaDto dadosCancelamentoConsultaDto) {

        if(dadosCancelamentoConsultaDto.motivo() == null){
            throw new ValidacaoException("O preenchimento do campo 'Motivo' é obrigatório");
        }
    }
}
