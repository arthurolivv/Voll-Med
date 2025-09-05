package med.voli.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private Long numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private Uf uf;
    private String cep;

    //dto -> entity
    public Endereco(EnderecoDto enderecoDto) {
        this.logradouro = enderecoDto.logradouro();
        this.numero = enderecoDto.numero();
        this.complemento = enderecoDto.complemento();
        this.bairro = enderecoDto.bairro();
        this.cidade = enderecoDto.cidade();
        this.uf = enderecoDto.uf();
        this.cep = enderecoDto.cep();
    }

    public void atualizarEndereco(EnderecoDto enderecoDto) {
        if(enderecoDto.logradouro() != null) {
            this.logradouro = enderecoDto.logradouro();
        }
        if(enderecoDto.numero() != null) {
            this.numero = enderecoDto.numero();
        }
        if(enderecoDto.complemento() != null) {
            this.complemento = enderecoDto.complemento();
        }
        if(enderecoDto.bairro() != null) {
            this.bairro = enderecoDto.bairro();
        }
        if(enderecoDto.cidade() != null) {
            this.cidade = enderecoDto.cidade();
        }
        if(enderecoDto.uf() != null) {
            this.uf = enderecoDto.uf();
        }
        if(enderecoDto.cep() != null) {
            this.cep = enderecoDto.cep();
        }
    }
}
