package com.my.test.web.rest;

import com.my.test.domain.PedidoItem;
import com.my.test.service.PedidoItemService;
import com.my.test.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.my.test.domain.PedidoItem}.
 */
@RestController
@RequestMapping("/api")
public class PedidoItemResource {

    private final Logger log = LoggerFactory.getLogger(PedidoItemResource.class);

    private static final String ENTITY_NAME = "testPedidoItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PedidoItemService pedidoItemService;

    public PedidoItemResource(PedidoItemService pedidoItemService) {
        this.pedidoItemService = pedidoItemService;
    }

    /**
     * {@code POST  /pedido-items} : Create a new pedidoItem.
     *
     * @param pedidoItem the pedidoItem to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pedidoItem, or with status {@code 400 (Bad Request)} if the pedidoItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pedido-items")
    public ResponseEntity<PedidoItem> createPedidoItem(@RequestBody PedidoItem pedidoItem) throws URISyntaxException {
        log.debug("REST request to save PedidoItem : {}", pedidoItem);
        if (pedidoItem.getId() != null) {
            throw new BadRequestAlertException("A new pedidoItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PedidoItem result = pedidoItemService.save(pedidoItem);
        return ResponseEntity.created(new URI("/api/pedido-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pedido-items} : Updates an existing pedidoItem.
     *
     * @param pedidoItem the pedidoItem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pedidoItem,
     * or with status {@code 400 (Bad Request)} if the pedidoItem is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pedidoItem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pedido-items")
    public ResponseEntity<PedidoItem> updatePedidoItem(@RequestBody PedidoItem pedidoItem) throws URISyntaxException {
        log.debug("REST request to update PedidoItem : {}", pedidoItem);
        if (pedidoItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PedidoItem result = pedidoItemService.save(pedidoItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pedidoItem.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pedido-items} : get all the pedidoItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pedidoItems in body.
     */
    @GetMapping("/pedido-items")
    public List<PedidoItem> getAllPedidoItems() {
        log.debug("REST request to get all PedidoItems");
        return pedidoItemService.findAll();
    }

    /**
     * {@code GET  /pedido-items/:id} : get the "id" pedidoItem.
     *
     * @param id the id of the pedidoItem to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pedidoItem, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pedido-items/{id}")
    public ResponseEntity<PedidoItem> getPedidoItem(@PathVariable Long id) {
        log.debug("REST request to get PedidoItem : {}", id);
        Optional<PedidoItem> pedidoItem = pedidoItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pedidoItem);
    }

    /**
     * {@code DELETE  /pedido-items/:id} : delete the "id" pedidoItem.
     *
     * @param id the id of the pedidoItem to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pedido-items/{id}")
    public ResponseEntity<Void> deletePedidoItem(@PathVariable Long id) {
        log.debug("REST request to delete PedidoItem : {}", id);
        pedidoItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
