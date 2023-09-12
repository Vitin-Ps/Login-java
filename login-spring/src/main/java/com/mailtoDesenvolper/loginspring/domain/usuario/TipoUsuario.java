package com.mailtoDesenvolper.loginspring.domain.usuario;

public enum TipoUsuario {
    ADMIN("admin");

    private String tipoUsuario;
    TipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }
}
