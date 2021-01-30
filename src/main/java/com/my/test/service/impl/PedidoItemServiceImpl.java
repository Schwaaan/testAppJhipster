package com.my.test.service.impl;

import com.my.test.service.PedidoItemService;
import com.my.test.domain.PedidoItem;
import com.my.test.repository.PedidoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link PedidoItem}.
 */
@Service
@Transactional
public class PedidoItemServiceImpl implements PedidoItemService {

    private final Logger log = LoggerFactory.getLogger(PedidoItemServiceImpl.class);

    private final PedidoItemRepository pedidoItemRepository;

    public PedidoItemServiceImpl(PedidoItemRepository pedidoItemRepository) {
        this.pedidoItemRepository = pedidoItemRepository;
    }

    @Override
    public PedidoItem save(PedidoItem pedidoItem) {
        log.debug("Request to save PedidoItem : {}", pedidoItem);
        return pedidoItemRepository.save(pedidoItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoItem> findAll() {
        log.debug("Request to get all PedidoItems");
        return pedidoItemRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PedidoItem> findOne(Long id) {
        log.debug("Request to get PedidoItem : {}", id);
        return pedidoItemRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PedidoItem : {}", id);
        pedidoItemRepository.deleteById(id);
    }
}
