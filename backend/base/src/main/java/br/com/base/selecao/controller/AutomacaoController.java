package br.com.base.selecao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.base.selecao.service.SelecaoAutomaticaService;

@RestController
@RequestMapping("/api/automacoes")
public class AutomacaoController {

    private final SelecaoAutomaticaService service;

    public AutomacaoController(SelecaoAutomaticaService service) {
        this.service = service;
    }

    @PostMapping("/cursos/{cursoId}/selecionar")
    public ResponseEntity<Void> selecionar(@PathVariable Long cursoId) {
        service.selecionarAleatorios(cursoId);
        return ResponseEntity.noContent().build();
    }
}
