package com.br.emakers.emprestimolivros.dto;

import com.br.emakers.emprestimolivros.models.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.sql.Date;

public record LivroDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String autor,
        @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-([0-9]{4})$\n")
        Date data_lancamento) {

    public LivroDTO(Livro livro){
        this(livro.getTitulo(), livro.getAutor(), livro.getData_lancamento());
    }
}
