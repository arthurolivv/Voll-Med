package med.voli.api.domain.pacientes;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import med.voli.api.domain.endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Paciente(PacienteDto pacienteDto) {
        this.nome = pacienteDto.nome();
        this.email = pacienteDto.email();
        this.telefone = pacienteDto.telefone();
        this.cpf = pacienteDto.cpf();
        this.endereco = new Endereco(pacienteDto.endereco());
        this.ativo = true;
    }

    public void desativarPaciente() {
        this.ativo = false;
    }

    public void atualizarDados(@Valid AtualizacaoPacienteDto pacienteDto) {
        if(pacienteDto.nome() != null) {
            this.nome = pacienteDto.nome();
        }
        if(pacienteDto.telefone() != null) {
            this.telefone = pacienteDto.telefone();
        }
        if(pacienteDto.endereco() != null) {
            this.endereco.atualizarEndereco(pacienteDto.endereco());
        }
    }
}
