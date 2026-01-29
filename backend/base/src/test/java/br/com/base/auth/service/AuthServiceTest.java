package br.com.base.auth.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.base.auth.dto.LoginDTO;
import br.com.base.auth.dto.UsuarioCadastroDTO;
import br.com.base.common.dto.LoginResponseDTO;
import br.com.base.usuario.dto.UsuarioDTO;
import br.com.base.usuario.model.Usuario;
import br.com.base.usuario.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    @InjectMocks
    private AuthService authService;

    @Test
    void deveCadastrarUsuarioComSucesso() {
        UsuarioCadastroDTO dto = new UsuarioCadastroDTO();
        dto.setNome("Maria");
        dto.setEmail("maria@email.com");
        dto.setSenha("123456");

        when(usuarioRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.empty());

        when(encoder.encode(dto.getSenha()))
                .thenReturn("senha-criptografada");

        Usuario salvo = new Usuario();
        salvo.setId(1L);
        salvo.setNome("Maria");
        salvo.setEmail("maria@email.com");
        salvo.setSenha("senha-criptografada");

        when(usuarioRepository.save(any(Usuario.class)))
                .thenReturn(salvo);

        UsuarioDTO response = authService.cadastrar(dto);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Maria", response.getNome());
        assertEquals("maria@email.com", response.getEmail());

        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void deveLancarErroQuandoEmailJaExistir() {
        UsuarioCadastroDTO dto = new UsuarioCadastroDTO();
        dto.setEmail("maria@email.com");

        when(usuarioRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.of(new Usuario()));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> authService.cadastrar(dto)
        );

        assertEquals("Email já cadastrado", exception.getMessage());

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void deveRealizarLoginComSucesso() {
        LoginDTO dto = new LoginDTO();
        dto.setEmail("maria@email.com");
        dto.setSenha("123456");

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setSenha("senha-criptografada");

        when(usuarioRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.of(usuario));

        when(encoder.matches(dto.getSenha(), usuario.getSenha()))
                .thenReturn(true);

        LoginResponseDTO response = authService.login(dto);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Maria", response.getNome());
        assertEquals("maria@email.com", response.getEmail());
    }

    @Test
    void deveLancarErroQuandoEmailNaoExistir() {
        LoginDTO dto = new LoginDTO();
        dto.setEmail("inexistente@email.com");

        when(usuarioRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> authService.login(dto)
        );

        assertEquals("Email ou senha inválidos", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoSenhaForInvalida() {
        LoginDTO dto = new LoginDTO();
        dto.setEmail("maria@email.com");
        dto.setSenha("errada");

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha("senha-criptografada");

        when(usuarioRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.of(usuario));

        when(encoder.matches(dto.getSenha(), usuario.getSenha()))
                .thenReturn(false);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> authService.login(dto)
        );

        assertEquals("Email ou senha inválidos", exception.getMessage());
    }
}
