package com.br.emakers.emprestimolivros.Repositories;

import com.br.emakers.emprestimolivros.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
