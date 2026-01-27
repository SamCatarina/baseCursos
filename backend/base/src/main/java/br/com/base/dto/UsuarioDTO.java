package br.com.base.dto;

import java.util.List;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;

    private List<Long> cursosInscritosIds;
    private List<Long> cursosSelecionadosIds;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getCursosInscritosIds() {
        return cursosInscritosIds;
    }

    public void setCursosInscritosIds(List<Long> cursosInscritosIds) {
        this.cursosInscritosIds = cursosInscritosIds;
    }

    public List<Long> getCursosSelecionadosIds() {
        return cursosSelecionadosIds;
    }

    public void setCursosSelecionadosIds(List<Long> cursosSelecionadosIds) {
        this.cursosSelecionadosIds = cursosSelecionadosIds;
    }
}
