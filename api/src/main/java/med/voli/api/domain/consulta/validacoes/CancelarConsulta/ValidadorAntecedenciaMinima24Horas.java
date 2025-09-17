package med.voli.api.domain.consulta.validacoes.CancelarConsulta;

import med.voli.api.domain.ValidacaoException;
import med.voli.api.domain.consulta.ConsultaRepository;
import med.voli.api.domain.consulta.DadosCancelamentoConsultaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorAntecedenciaMinima24Horas implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsultaDto dadosCancelamentoConsultaDto) {

        var consulta = consultaRepository.getReferenceById(dadosCancelamentoConsultaDto.idConsulta());

        if(consulta.getData().isAfter(LocalDateTime.now().plusHours(24))){
            throw new ValidacaoException("A consulta só pode ser cancelada com no mínimo 24 horas de antecedência!");
        }
    }
}
