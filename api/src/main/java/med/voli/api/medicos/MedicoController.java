package med.voli.api.medicos;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;
    //@Autowired e como se fizesse isso:

    //    public PedidoService() {
    //        this.pagamentoService = new PagamentoService(); //criado manualmente
    //    }

    @PostMapping
    @Transactional //para mexer no bando de dados (mexer nele)
    public void cadastrar(@RequestBody @Valid MedicoDto medicoDto) {
        //@RequestBody - Informa que a informacao vem do corpo da requisicao
        //do JSON
        //System.out.println(medicoDto);
        medicoRepository.save(new Medico(medicoDto)); //precisou criar um construtor em medico
        // para pegar os dados de dto
    }

    @GetMapping
    //nao precisa de transaction pois e so leitra, n tem criacao, atualizacao nem delecao
    public Page<ListagemMedicoDto> listar (@PageableDefault(size=2, sort={"nome"} ) Pageable paginacao){
        //Page<> para retornar informacoes sobre a pagincacao, se n quiser use so List<>
        //return medicoRepository.findAll(paginacao).stream().map(ListagemMedicoDto::new).toList();
        return medicoRepository.findAllByAtivoTrue(paginacao).map(ListagemMedicoDto::new);
        //Page<> ja faz o q stream() e .toList() faz
        //converte uma lista de medicos para ListagemMedicosDto
        //O spring criou essa funcao so nomeando, listando apenas os medico q tem ativo = true
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizacaoMedicoDto medicoDto) {
        var medico = medicoRepository.getReferenceById(medicoDto.id());
        medico.atualizarDados(medicoDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void desativar(@PathVariable Long id){
        //medicoRepository.deleteById(id);
        var medico = medicoRepository.getReferenceById(id);
        medico.desativarMedico();
    }


}
