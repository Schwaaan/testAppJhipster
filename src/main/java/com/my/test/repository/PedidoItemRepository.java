package com.my.test.repository;

import com.my.test.domain.PedidoItem;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PedidoItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long> {
}
