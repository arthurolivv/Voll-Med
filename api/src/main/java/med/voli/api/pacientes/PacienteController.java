package med.voli.api.pacientes;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PacienteDto pacientesDto) {
        pacienteRepository.save(new Paciente(pacientesDto));
    }

    @GetMapping
    public Page<ListagemPacienteDto> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){
        return pacienteRepository.findAllByAtivoTrue(paginacao).map(ListagemPacienteDto::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizacaoPacienteDto pacienteDto) {
        var paciente = pacienteRepository.getReferenceById(pacienteDto.id());
        paciente.atualizarDados(pacienteDto);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.desativarPaciente();
    }
}
