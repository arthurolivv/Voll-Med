package med.voli.api.controller;

import jakarta.validation.Valid;
import med.voli.api.domain.usuario.Usuario;
import med.voli.api.infra.security.DadosTokenJwtDto;
import med.voli.api.infra.security.TokenService;
import med.voli.api.usuario.DadosAutenticacaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDto dadosAutenticacaoDto){
try {
    var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacaoDto.login(), dadosAutenticacaoDto.senha());
    var authentication = authenticationManager.authenticate(authenticationToken);

    //return ResponseEntity.ok().build();
    var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

    return ResponseEntity.ok(new DadosTokenJwtDto(tokenJWT));
}catch (Exception e) {
    e.printStackTrace();
    return ResponseEntity.badRequest().body(e.getMessage());
}
    }
}
