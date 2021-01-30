package com.my.test.service.impl;

import com.my.test.service.ProdutoService;
import com.my.test.domain.Produto;
import com.my.test.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Produto}.
 */
@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {

    private final Logger log = LoggerFactory.getLogger(ProdutoServiceImpl.class);

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto save(Produto produto) {
        log.debug("Request to save Produto : {}", produto);
        return produtoRepository.save(produto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Produto> findAll(Pageable pageable) {
        log.debug("Request to get all Produtos");
        return produtoRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Produto> findOne(Long id) {
        log.debug("Request to get Produto : {}", id);
        return produtoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Produto : {}", id);
        produtoRepository.deleteById(id);
    }
}
