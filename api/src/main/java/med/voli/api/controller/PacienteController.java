package med.voli.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voli.api.domain.pacientes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid PacienteDto pacientesDto, UriComponentsBuilder uriBuilder) {
        var paciente = pacienteRepository.save(new Paciente(pacientesDto));
        var uri =  uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPacienteDto(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPacienteDto>> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){

        var page = pacienteRepository.findAllByAtivoTrue(paginacao).map(ListagemPacienteDto::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizacaoPacienteDto pacienteDto) {
        var paciente = pacienteRepository.getReferenceById(pacienteDto.id());
        paciente.atualizarDados(pacienteDto);
        return ResponseEntity.ok(new DadosDetalhamentoPacienteDto(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.desativarPaciente();

        return ResponseEntity.noContent().build();
    }
}
