package med.voli.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record EnderecoDto(

        @NotBlank
        String logradouro,

        Long numero,

        String complemento,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        @NotNull
        Uf uf,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep) {
}
