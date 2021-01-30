package com.my.test.service;

import com.my.test.domain.PedidoItem;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link PedidoItem}.
 */
public interface PedidoItemService {

    /**
     * Save a pedidoItem.
     *
     * @param pedidoItem the entity to save.
     * @return the persisted entity.
     */
    PedidoItem save(PedidoItem pedidoItem);

    /**
     * Get all the pedidoItems.
     *
     * @return the list of entities.
     */
    List<PedidoItem> findAll();


    /**
     * Get the "id" pedidoItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PedidoItem> findOne(Long id);

    /**
     * Delete the "id" pedidoItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
