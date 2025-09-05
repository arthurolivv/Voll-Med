package med.voli.api.domain.pacientes;

import jakarta.validation.constraints.NotNull;
import med.voli.api.domain.endereco.EnderecoDto;

public record AtualizacaoPacienteDto (

        @NotNull
        Long id,

        String nome,

        String telefone,

        EnderecoDto endereco
){
}
