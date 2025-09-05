package med.voli.api.domain.medicos;

import jakarta.validation.constraints.NotNull;
import med.voli.api.domain.endereco.EnderecoDto;

public record AtualizacaoMedicoDto(

        @NotNull
        Long id,

        String nome,

        String telefone,

        EnderecoDto endereco) { }
