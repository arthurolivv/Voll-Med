package med.voli.api.pacientes;

import jakarta.validation.constraints.NotNull;
import med.voli.api.endereco.EnderecoDto;

public record AtualizacaoPacienteDto (

        @NotNull
        Long id,

        String nome,

        String telefone,

        EnderecoDto endereco
){
}
