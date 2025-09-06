package med.voli.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TradadorDeErros {

    //Essa classe altera o padrao do retorno de Erro do Spring boot (erro 500) para erro 404 not found
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException exceptionRecebida){
        var erros = exceptionRecebida.getFieldErrors(); //pega e armazena a lista de erros que aconteceram na exception
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacaoDto::new).toList());
    }

    private record DadosErroValidacaoDto(String campo, String mensagem){

        public DadosErroValidacaoDto(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
