package com.gabriel.helpdesk.domain.enums;

import java.util.Objects;

public enum Perfil {

    ADMIN(0, "ROLE_ADMIN"),
    CLIENTE(1,"ROLE_CLIENTE"),
    TECNICO(2,"ROLE_TECNICO");

    private Integer codigo;
    private String descricao;

    Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod){
        if(Objects.isNull(cod)){
            return null;
        }

        for (Perfil x : Perfil.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil inválido");
    }

}
