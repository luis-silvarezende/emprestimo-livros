package com.br.emakers.emprestimolivros.dto;

import com.br.emakers.emprestimolivros.models.Pessoa;

public record PessoaDTO(String nome, String cep) {

    public PessoaDTO(Pessoa pessoa) {
        this(pessoa.getNome(), pessoa.getCep());
    }
}

