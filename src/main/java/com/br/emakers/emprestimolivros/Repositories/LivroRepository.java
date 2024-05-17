package com.br.emakers.emprestimolivros.Repositories;

import com.br.emakers.emprestimolivros.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
