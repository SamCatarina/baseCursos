package br.com.base.dto;

import java.util.Set;

public class SelecionarUsuariosRequest {

    private Set<Long> usuarioIds;

    public Set<Long> getUsuarioIds() {
        return usuarioIds;
    }

    public void setUsuarioIds(Set<Long> usuarioIds) {
        this.usuarioIds = usuarioIds;
    }
}
