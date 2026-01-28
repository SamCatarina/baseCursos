package br.com.base.event.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.base.event.dto.CursoEmailEvent;

@Service
public class CursoEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public CursoEventProducer(
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void enviarEmailInscricao(Long cursoId, Long usuarioId) {
        CursoEmailEvent event = new CursoEmailEvent();
        event.setCursoId(cursoId);
        event.setUsuarioId(usuarioId);
        event.setTipo("INSCRICAO");

        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("email-curso", json);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao serializar evento", e);
        }
    }

    public void enviarEmailSelecao(Long cursoId, Long usuarioId) {
        CursoEmailEvent event = new CursoEmailEvent();
        event.setCursoId(cursoId);
        event.setUsuarioId(usuarioId);
        event.setTipo("SELECAO");

        try {
            String json = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("email-curso", json);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao serializar evento", e);
        }
    }
}
