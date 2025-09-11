package med.voli.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voli.api.domain.consulta.DadosAgendamentoConsultaDto;
import med.voli.api.domain.consulta.DadosDetalhamentoConsultaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsultaDto dadosAgendamentoConsultaDto){

        System.out.println(dadosAgendamentoConsultaDto);
        return ResponseEntity.ok(new DadosDetalhamentoConsultaDto(null, null, null, null));
    }
}
