package med.voli.api.medicos;

import jakarta.validation.constraints.NotNull;
import med.voli.api.endereco.EnderecoDto;

public record AtualizacaoMedicoDto(

        @NotNull
        Long id,

        String nome,

        String telefone,

        EnderecoDto endereco) { }
