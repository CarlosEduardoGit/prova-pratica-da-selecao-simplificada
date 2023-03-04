package com.fila.pessoa.services;

import com.fila.pessoa.model.PessoaModel;
import com.fila.pessoa.specifications.SpecificationTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface PessoaService {
    Page<PessoaModel> findAll(Specification<PessoaModel> spec, Pageable pageable);

    Optional<PessoaModel> findById(UUID pessoaId);

    PessoaModel save(PessoaModel pessoaModel);

    boolean existsByPessoaName(String nome);

    void delete(PessoaModel pessoaModel);
}