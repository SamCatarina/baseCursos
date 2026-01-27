package br.com.base.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.base.dto.UsuarioDTO;
import br.com.base.model.Curso;
import br.com.base.model.Usuario;
import br.com.base.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    // 🔹 LISTAR
    public List<UsuarioDTO> listarDTO() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // 🔹 SALVAR
    public UsuarioDTO salvarDTO(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());

        Usuario salvo = repository.save(usuario);
        return toDTO(salvo);
    }

    // 🔹 CONVERSÃO
    public UsuarioDTO toDTO(Usuario usuario) {

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());

        dto.setCursosInscritosIds(
                usuario.getCursosInscritos()
                        .stream()
                        .map(Curso::getId)
                        .toList()
        );

        dto.setCursosSelecionadosIds(
                usuario.getCursosSelecionados()
                        .stream()
                        .map(Curso::getId)
                        .toList()
        );

        return dto;
    }
}
