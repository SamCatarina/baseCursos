package br.com.base.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CURSO")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "professor_id", nullable = false)
    private Usuario professor;

    @Column
    private String descricao;

    // 🔹 INSCRITOS
    @ManyToMany
    @JoinTable(
            name = "CURSO_INSCRITOS",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> inscritos;

    // 🔹 SELECIONADOS
    @ManyToMany
    @JoinTable(
            name = "CURSO_SELECIONADOS",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> selecionados;

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

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Usuario> getInscritos() {
        return inscritos;
    }

    public void setInscritos(Set<Usuario> inscritos) {
        this.inscritos = inscritos;
    }

    public Set<Usuario> getSelecionados() {
        return selecionados;
    }

    public void setSelecionados(Set<Usuario> selecionados) {
        this.selecionados = selecionados;
    }

}
