package com.gabriel.helpdesk.domain.enums;

import java.util.Objects;

public enum Status {

    ABERTO(0, "ABERTO"),
    ANDAMENTO(1,"ANDAMENTO"),
    ENCERRADO(2,"ENCERRADO");

    private Integer codigo;
    private String descricao;

    Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer cod){
        if(Objects.isNull(cod)){
            return null;
        }

        for (Status x : Status.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Status inválido");
    }

}
