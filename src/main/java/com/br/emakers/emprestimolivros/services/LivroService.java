package com.br.emakers.emprestimolivros.services;

import com.br.emakers.emprestimolivros.Repositories.LivroRepository;
import com.br.emakers.emprestimolivros.Repositories.PessoaRepository;
import com.br.emakers.emprestimolivros.dto.EmprestimoRequestDTO;
import com.br.emakers.emprestimolivros.dto.LivroDTO;
import com.br.emakers.emprestimolivros.exceptions.geral.EntityNotFoundException;
import com.br.emakers.emprestimolivros.models.Livro;
import com.br.emakers.emprestimolivros.models.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<LivroDTO> getAll(){
        List<Livro> livros = livroRepository.findAll();

        return livros.stream().map(LivroDTO::new).collect(Collectors.toList());
    }

    public LivroDTO findById(Long idLivro){
        Livro livro = encontrarLivroId(idLivro);
        LivroDTO dto = new LivroDTO(livro);
        return dto;
    }

    public LivroDTO createLivro(LivroDTO livroDTO){
        Livro livro = new Livro(livroDTO);
        livroRepository.save(livro);

        return new LivroDTO(livro);
    }

    public LivroDTO updateLivro(Long idLivro, LivroDTO livroDTO){
        Livro livro = encontrarLivroId(idLivro);
        livro.setTitulo(livroDTO.titulo());
        livro.setAutor(livroDTO.autor());
        livro.setData_lancamento(livroDTO.data_lancamento());
        livroRepository.save(livro);

        return new LivroDTO(livro);
    }

    public String deleteLivro(Long idLivro){
        Livro livro = encontrarLivroId(idLivro);
        livroRepository.delete(livro);

        return "Livro excluido com sucesso.";
    }

    public LivroDTO emprestimo(EmprestimoRequestDTO emprestimoRequestDTO){
        Livro livro = encontrarLivroId(emprestimoRequestDTO.idLivro());
        Pessoa pessoa = pessoaRepository.findById(emprestimoRequestDTO.idPessoa())
                .orElseThrow(() -> new EntityNotFoundException(emprestimoRequestDTO.idPessoa()));

        livro.setPessoa(pessoa);
        livroRepository.save(livro);

        pessoa.setLivro(livro);
        pessoaRepository.save(pessoa);

        return new LivroDTO(livro);
    }

    public ResponseEntity<String> devolver(EmprestimoRequestDTO emprestimoRequestDTO) {
        Livro livro = encontrarLivroId(emprestimoRequestDTO.idLivro());
        Pessoa pessoa = pessoaRepository.findById(emprestimoRequestDTO.idPessoa())
                .orElseThrow(() -> new EntityNotFoundException(emprestimoRequestDTO.idPessoa()));

        if (!livro.getPessoas().contains(pessoa)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Este livro não está emprestado para esta pessoa.");
        }

        livro.removePessoas(pessoa);
        livroRepository.save(livro);

        pessoa.removeLivro(livro);
        pessoaRepository.save(pessoa);

        return ResponseEntity.ok().body("Livro devolvido com sucesso.");

    }

    public Livro encontrarLivroId(Long idLivro){
        return livroRepository.findById(idLivro).orElseThrow(() -> new EntityNotFoundException(idLivro));
    }
}
