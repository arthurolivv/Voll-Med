package med.voli.api.domain.consulta.validacoes.CancelarConsulta;

import med.voli.api.domain.consulta.DadosCancelamentoConsultaDto;

public interface ValidadorCancelamentoDeConsulta {

     void validar(DadosCancelamentoConsultaDto dadosCancelamentoConsultaDto);
}
