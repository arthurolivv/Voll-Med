package med.voli.api.infra.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voli.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //carrega uma classe ou componente generico
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //o envio do token e realizado atraves de um cabecalho http chamado "authorization"
        var tokenJwt = recuperarToken(request);

        if(tokenJwt != null) {
            var subject = tokenService.getSubject(tokenJwt);
            System.out.println("Token subject: " + subject);

            var usuario = usuarioRepository.findByLogin(subject);


            if (usuario == null) {
                System.out.println("Usuário não encontrado no banco!");
            } else {
                System.out.println("Usuário encontrado: " + usuario.getUsername());
                System.out.println("Authorities: " + usuario.getAuthorities());
            }

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication); //spring considera q vc esta logado forcadamente
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");
//        if(authorizationHeader == null){
//            throw new RuntimeException("Token JWT nao enviado no cabecalho authorization");
//        }
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "").trim(); //retira o prefixo que vem junto com o token no cabecalho
        }
        return null;

    }
}
