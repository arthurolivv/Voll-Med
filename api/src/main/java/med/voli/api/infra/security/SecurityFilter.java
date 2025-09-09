package med.voli.api.infra.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //carrega uma classe ou componente generico
public class SecurityFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //o envio do token e realizado atraves de um cabecalho http chamado "authorization"
        var tokenJwt = recuperarToken(request);
        System.out.println(tokenJwt);
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null){
            throw new RuntimeException("Token JWT nao enviado no cabecalho authorization");
        }
        return authorizationHeader.replace("Bearer ", ""); //retira o prefixo que vem junto com o token no cabecalho
    }
}
