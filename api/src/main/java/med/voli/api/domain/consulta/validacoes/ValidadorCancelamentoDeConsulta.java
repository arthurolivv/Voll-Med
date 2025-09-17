package med.voli.api.domain.consulta.validacoes;

import med.voli.api.domain.consulta.DadosCancelamentoConsultaDto;

public interface ValidadorCancelamentoDeConsulta {

     void validar(DadosCancelamentoConsultaDto dadosCancelamentoConsultaDto);
}
