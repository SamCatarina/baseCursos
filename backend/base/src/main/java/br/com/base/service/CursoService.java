package br.com.base.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.base.dto.CursoDTO;
import br.com.base.dto.UsuarioDTO;
import br.com.base.model.Curso;
import br.com.base.model.Usuario;
import br.com.base.repository.CursoRepository;
import br.com.base.repository.UsuarioRepository;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    public CursoService(CursoRepository cursoRepository,
            UsuarioRepository usuarioRepository) {
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // 🔹 LISTAR
    public List<CursoDTO> listarDTO() {
        return cursoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // 🔹 SALVAR
    public CursoDTO salvarDTO(CursoDTO dto) {
        Curso curso = new Curso();
        curso.setNome(dto.getNome());
        curso.setDescricao(dto.getDescricao());

        Usuario professor = usuarioRepository.findById(dto.getProfessorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        curso.setProfessor(professor);

        Curso salvo = cursoRepository.save(curso);
        return toDTO(salvo);
    }

    // BUSCAR POR ID
    public CursoDTO buscarPorIdDTO(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        return toDTO(curso);
    }

    // 🔹 INSCRIÇÃO
    public void inscreverUsuario(Long cursoId, Long usuarioId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        curso.getInscritos().add(usuario);
        cursoRepository.save(curso);
    }

    // 🔹 SELEÇÃO
    public void selecionarUsuario(Long cursoId, Set<Long> usuarioIds) {

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Set<Usuario> usuarios = usuarioRepository.findAllById(usuarioIds)
                .stream()
                .collect(Collectors.toSet());

        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado");
        }

        curso.getSelecionados().addAll(usuarios);

        cursoRepository.save(curso);
    }

    // 🔹 CONVERSÃO
    public CursoDTO toDTO(Curso curso) {
        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setNome(curso.getNome());
        dto.setDescricao(curso.getDescricao());
        dto.setProfessorId(curso.getProfessor().getId());

        dto.setInscritos(
                curso.getInscritos()
                        .stream()
                        .map(Usuario::getId)
                        .collect(Collectors.toSet())
        );

        dto.setSelecionados(
                curso.getSelecionados()
                        .stream()
                        .map(Usuario::getId)
                        .collect(Collectors.toSet())
        );

        return dto;
    }

    public UsuarioDTO toDTO(Usuario usuario) {

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());

        dto.setCursosInscritosIds(
                usuario.getCursosInscritos()
                        .stream()
                        .map(Curso::getId)
                        .toList());

        dto.setCursosSelecionadosIds(
                usuario.getCursosSelecionados()
                        .stream()
                        .map(Curso::getId)
                        .toList());

        return dto;

    }

}
