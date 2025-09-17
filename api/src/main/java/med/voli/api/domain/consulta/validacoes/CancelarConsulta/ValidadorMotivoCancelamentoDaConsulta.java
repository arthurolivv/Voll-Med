package med.voli.api.domain.consulta.validacoes.CancelarConsulta;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.DadosCancelamentoConsultaDto;
import med.voli.api.domain.consulta.MotivoCancelamento;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMotivoCancelamentoDaConsulta implements ValidadorCancelamentoDeConsulta {

    @Override
    public void validar(DadosCancelamentoConsultaDto dadosCancelamentoConsultaDto) {

        if(dadosCancelamentoConsultaDto.motivo() == null || !verificaOpcoesMotivoCancelamento(dadosCancelamentoConsultaDto)){
            throw new ValidacaoException("O preenchimento do campo 'Motivo' é obrigatório");
        }

    }

    public boolean verificaOpcoesMotivoCancelamento(DadosCancelamentoConsultaDto dadosCancelamentoConsultaDto){
            if(dadosCancelamentoConsultaDto.motivo() != MotivoCancelamento.OUTROS){
                return false;
            } else if (dadosCancelamentoConsultaDto.motivo() != MotivoCancelamento.PACIENTE_DESISTIU) {
                return false;

            } else if (dadosCancelamentoConsultaDto.motivo()!= MotivoCancelamento.MEDICO_CANCELOU) {
                return false;

            }
            else {
                return true;
            }
    }
}
