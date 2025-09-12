package med.voli.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voli.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendamentoDeConsultas agendamentoDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto){

        var dto = agendamentoDeConsultas.agendar(dadosAgendamentoConsultaDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsultaDto dadosCancelamentoConsulta){

        agendamentoDeConsultas.cancelar(dadosCancelamentoConsulta);
        return  ResponseEntity.noContent().build();
    }
}
