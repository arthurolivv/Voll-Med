package med.voli.api.medicos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voli.api.endereco.EnderecoDto;

public record MedicoDto(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //de 4 a 6 digitos
        String crm,

        @NotNull
        Especiality especialidade,

        @NotNull
        @Valid //validar esse outro objeto como um dos atributos
        EnderecoDto endereco) {
    //Esse record e um padrao DTO
    //controla os dados de entrada e saida para a API SEM NENHUM TIPO DE COMPORTAMENTO
    //Record e uma classe imutavel
}
