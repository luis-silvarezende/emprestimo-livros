package com.br.emakers.emprestimolivros.models;

import com.br.emakers.emprestimolivros.dto.PessoaDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;
    private String nome;
    private String cep;

    @ManyToMany
    @JoinTable(name = "tb_emprestimo",
            joinColumns = @JoinColumn(name = "idPessoa_fk"),
            inverseJoinColumns = @JoinColumn(name = "idLivro_fk")
    )
    private List<Livro> livros = new ArrayList<>();

    @Builder
    public Pessoa(PessoaDTO pessoaRequestDTO) {
        nome = pessoaRequestDTO.nome();
        cep = pessoaRequestDTO.cep();
    }

    public void setLivro(Livro livro){
        livros.add(livro);
    }

    public void removeLivro(Livro livro){livros.remove(livro);}
}
