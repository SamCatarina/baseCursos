package br.com.base.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.base.dto.CursoDTO;
import br.com.base.dto.SelecionarUsuariosRequest;

@Service
public class SelecaoAutomaticaService {

    private final RestTemplate restTemplate;

    public SelecaoAutomaticaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void selecionarAleatorios(Long cursoId) {

        // 1️⃣ Buscar curso
        CursoDTO curso = restTemplate.getForObject(
                "http://localhost:8080/api/cursos/" + cursoId,
                CursoDTO.class
        );

        if (curso.getInscritos().size() < 2) {
            throw new RuntimeException("Poucos inscritos");
        }

        // 2️⃣ Escolher 2 aleatórios
        List<Long> inscritos = new ArrayList<>(curso.getInscritos());

        Collections.shuffle(inscritos);

        Set<Long> selecionados = inscritos.stream()
                .limit(2)
                .collect(Collectors.toSet());

        // 3️⃣ Enviar seleção
        SelecionarUsuariosRequest body = new SelecionarUsuariosRequest();
        body.setUsuarioIds(selecionados);

        restTemplate.postForEntity(
                "http://localhost:8080/api/cursos/" + cursoId + "/selecoes",
                body,
                Void.class
        );
    }
}
