package br.com.base.auth.controller;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.base.auth.dto.LoginDTO;
import br.com.base.auth.dto.UsuarioCadastroDTO;
import br.com.base.auth.service.AuthService;
import br.com.base.common.dto.LoginResponseDTO;
import br.com.base.usuario.dto.UsuarioDTO;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @Test
    void deveCadastrarUsuarioComSucesso() throws Exception {
        UsuarioCadastroDTO request = new UsuarioCadastroDTO();
        request.setNome("Maria");
        request.setEmail("maria@email.com");
        request.setSenha("123456");

        UsuarioDTO response = new UsuarioDTO();
        response.setId(1L);
        response.setNome("Maria");
        response.setEmail("maria@email.com");

        when(authService.cadastrar(any()))
                .thenReturn(response);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Maria"))
                .andExpect(jsonPath("$.email").value("maria@email.com"));
    }

    @Test
    void deveRealizarLoginComSucesso() throws Exception {
        LoginDTO request = new LoginDTO();
        request.setEmail("maria@email.com");
        request.setSenha("123456");

        LoginResponseDTO response = new LoginResponseDTO();

        when(authService.login(any()))
                .thenReturn(response);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
