package med.voli.api.domain.consulta.validacoes;

import med.voli.api.domain.consulta.DadosAgendamentoConsultaDto;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto);
}
