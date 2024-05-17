package com.br.emakers.emprestimolivros.dto;

import com.br.emakers.emprestimolivros.models.EmprestimoRequest;

public record EmprestimoRequestDTO(Long idLivro, Long idPessoa) {
    public EmprestimoRequestDTO(EmprestimoRequest emprestimoRequest){
        this(emprestimoRequest.getIdLivro(), emprestimoRequest.getIdPessoa());
    }
}
