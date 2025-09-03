package med.voli.api.medicos;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import med.voli.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor //Gera o construtor sem argumentos que a JPA exige
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //compara e compreende que duas instancias
// de Medico sao instancias diferentes, mesmo que possuam o mesmo ID
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especiality especialidade;

    @Embedded
    //fica numa classe separada mas considera que os campos
    //fazem parta da mesma tabela medicos
    private Endereco endereco;

    private Boolean ativo;

    //dto -> entity
    public Medico(MedicoDto medicoDto) {
        this.nome = medicoDto.nome();
        this.email = medicoDto.email();
        this.telefone = medicoDto.telefone();
        this.crm = medicoDto.crm();
        this.especialidade = medicoDto.especialidade();
        this.endereco = new Endereco(medicoDto.endereco());
        this.ativo = true;
    }

    public void atualizarDados(@Valid AtualizacaoMedicoDto medicoDto) {
        if(medicoDto.nome() != null) {
            this.nome = medicoDto.nome();
        }
        if(medicoDto.telefone() != null) {
            this.telefone = medicoDto.telefone();
        }
        if(medicoDto.endereco() != null) {
            this.endereco.atualizarEndereco(medicoDto.endereco());
        }
    }

    public void desativarMedico() {
        this.ativo = false;
    }
}
