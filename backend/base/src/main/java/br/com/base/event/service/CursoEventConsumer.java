package br.com.base.event.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class CursoEventConsumer {

    @PostConstruct
    public void init() {
        System.out.println("CursoEventConsumer CARREGADO");
    }

    @KafkaListener(
            topics = "email-curso",
            groupId = "email-service"
    )
    public void consumir(String mensagem) {
        System.out.println("MENSAGEM RECEBIDA:");
        System.out.println(mensagem);
    }
}
