package com.fila.pessoa.services.impl;

import com.fila.pessoa.model.PessoaModel;
import com.fila.pessoa.repositories.PessoaRepository;
import com.fila.pessoa.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaServiceImpl implements PessoaService {
    @Autowired
    PessoaRepository pessoaRepository;

    @Override
    public Page<PessoaModel> findAll(Specification<PessoaModel> spec, Pageable pageable) {
        return pessoaRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<PessoaModel> findById(UUID pessoaId) {
        return pessoaRepository.findById(pessoaId);
    }

    @Override
    public PessoaModel save(PessoaModel pessoaModel) {
        return pessoaRepository.save(pessoaModel);
    }

    @Override
    public boolean existsByPessoaName(String nome) {
        return pessoaRepository.existsByNome(nome);
    }

    @Override
    public void delete(PessoaModel pessoaModel) {
        pessoaRepository.delete(pessoaModel);
    }
}
