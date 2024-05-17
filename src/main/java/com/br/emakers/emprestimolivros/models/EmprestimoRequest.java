package com.br.emakers.emprestimolivros.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmprestimoRequest {

    private Long idLivro;
    private Long idPessoa;

    @Builder
    public  EmprestimoRequest(Long idLivro, Long idPessoa){
        this.idLivro = idLivro;
        this.idPessoa = idPessoa;
    }

}
