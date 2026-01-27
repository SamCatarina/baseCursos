package br.com.base.dto;

import java.util.Set;

public class CursoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Long professorId;
    private Set<Long> inscritos;
    private Set<Long> selecionados;

    // getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public Set<Long> getInscritos() {
        return inscritos;
    }

    public void setInscritos(Set<Long> inscritos) {
        this.inscritos = inscritos;
    }

    public Set<Long> getSelecionados() {
        return selecionados;
    }

    public void setSelecionados(Set<Long> selecionados) {
        this.selecionados = selecionados;
    }
}
