package com.br.emakers.emprestimolivros.services;

import com.br.emakers.emprestimolivros.Repositories.PessoaRepository;
import com.br.emakers.emprestimolivros.dto.PessoaDTO;
import com.br.emakers.emprestimolivros.exceptions.geral.EntityNotFoundException;
import com.br.emakers.emprestimolivros.models.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaDTO> getAll(){
        List<Pessoa> pessoas = pessoaRepository.findAll();

        return pessoas.stream().map(PessoaDTO::new).collect(Collectors.toList());
    }

    public PessoaDTO findById(Long idPessoa){
        Pessoa pessoa = encontrarPessoaId(idPessoa);
        PessoaDTO dto = new PessoaDTO(pessoa);
        return dto;
    }

    public PessoaDTO createPessoa(PessoaDTO pessoaDTO){
        Pessoa pessoa = new Pessoa(pessoaDTO);
        pessoaRepository.save(pessoa);

        return new PessoaDTO(pessoa);
    }

    public PessoaDTO updatePessoa(Long idPessoa, PessoaDTO pessoaDTO){
        Pessoa pessoa = encontrarPessoaId(idPessoa);
        pessoa.setNome(pessoaDTO.nome());
        pessoa.setCep(pessoaDTO.cep());
        pessoaRepository.save(pessoa);

        return new PessoaDTO(pessoa);
    }

    public String deletePessoa(Long idPessoa){
        Pessoa pessoa = encontrarPessoaId(idPessoa);
        pessoaRepository.delete(pessoa);
        return "Pessoa excluida com sucesso.";
    }

    private Pessoa encontrarPessoaId(Long idPessoa){
        return pessoaRepository.findById(idPessoa).orElseThrow(() -> new EntityNotFoundException(idPessoa));
    }
}
