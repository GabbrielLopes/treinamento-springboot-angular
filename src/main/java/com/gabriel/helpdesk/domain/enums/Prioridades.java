package com.gabriel.helpdesk.domain.enums;

import java.util.Objects;

public enum Prioridades {

    BAIXA(0, "BAIXA"),
    MEDIA(1,"MEDIA"),
    ALTA(2,"ALTA");

    private Integer codigo;
    private String descricao;

    Prioridades(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridades toEnum(Integer cod){
        if(Objects.isNull(cod)){
            return null;
        }

        for (Prioridades x : Prioridades.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade inválida");
    }

}
