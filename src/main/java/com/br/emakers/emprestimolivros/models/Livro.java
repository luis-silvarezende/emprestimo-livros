package com.br.emakers.emprestimolivros.models;

import com.br.emakers.emprestimolivros.dto.LivroDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;
    private String titulo;
    private String autor;
    private Date data_lancamento;

    @ManyToMany(mappedBy = "livros")
    private List<Pessoa> pessoas = new ArrayList<>();

    @Builder
    public Livro (LivroDTO livroDTO){
        titulo = livroDTO.titulo();
        autor = livroDTO.autor();
        data_lancamento = livroDTO.data_lancamento();
    }

    public void setPessoa(Pessoa pessoa){
        pessoas.add(pessoa);
    }

    public void removePessoas(Pessoa pessoa){
        pessoas.remove(pessoa);
    }

    public boolean encontrarPessoa(){
        return pessoas.isEmpty();
    }
}
