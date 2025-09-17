package med.voli.api.domain.consulta.validacoes.agendarConsulta;

import med.voli.api.domain.consulta.DadosAgendamentoConsultaDto;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto);
}
