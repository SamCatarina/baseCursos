package br.com.base.curso.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.base.curso.dto.CursoDTO;
import br.com.base.curso.dto.SelecionarUsuariosRequest;
import br.com.base.curso.service.CursoService;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    // 🔹 LISTAR CURSOS
    @GetMapping
    public List<CursoDTO> listar() {
        return service.listarDTO();
    }

    // 🔹 CRIAR CURSO
    @PostMapping
    public ResponseEntity<CursoDTO> criar(@RequestBody CursoDTO dto) {
        CursoDTO salvo = service.salvarDTO(dto);
        return ResponseEntity.ok(salvo);
    }

    // BUSCAR UM CURSO
    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> buscarPorId(@PathVariable Long id) {
        CursoDTO dto = service.buscarPorIdDTO(id);
        return ResponseEntity.ok(dto);
    }

    // 🔹 INSCRIÇÃO
    @PostMapping("/{cursoId}/inscricoes/{usuarioId}")
    public ResponseEntity<Void> inscreverUsuario(
            @PathVariable Long cursoId,
            @PathVariable Long usuarioId
    ) {
        service.inscreverUsuario(cursoId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    // 🔹 SELEÇÃO
    @PostMapping("/{cursoId}/selecoes")
    public ResponseEntity<Void> selecionarUsuarios(
            @PathVariable Long cursoId,
            @RequestBody SelecionarUsuariosRequest request
    ) {
        service.selecionarUsuario(cursoId, request.getUsuarioIds());
        return ResponseEntity.noContent().build();
    }

}
