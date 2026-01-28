package br.com.base.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.base.auth.dto.LoginDTO;
import br.com.base.auth.dto.UsuarioCadastroDTO;
import br.com.base.auth.service.AuthService;
import br.com.base.common.dto.LoginResponseDTO;
import br.com.base.usuario.dto.UsuarioDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    // 🔹 CADASTRO
    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> cadastrar(
            @RequestBody UsuarioCadastroDTO dto
    ) {
        return ResponseEntity.ok(service.cadastrar(dto));
    }

    // 🔹 LOGIN
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginDTO dto
    ) {
        return ResponseEntity.ok(service.login(dto));
    }
}
