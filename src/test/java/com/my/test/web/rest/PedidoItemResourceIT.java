package com.my.test.web.rest;

import com.my.test.TestApp;
import com.my.test.domain.PedidoItem;
import com.my.test.repository.PedidoItemRepository;
import com.my.test.service.PedidoItemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PedidoItemResource} REST controller.
 */
@SpringBootTest(classes = TestApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PedidoItemResourceIT {

    private static final String DEFAULT_QUANTIDADE = "AAAAAAAAAA";
    private static final String UPDATED_QUANTIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_VALOR_DO_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_VALOR_DO_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_VALOR_TOTAL = "AAAAAAAAAA";
    private static final String UPDATED_VALOR_TOTAL = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVACAOO = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACAOO = "BBBBBBBBBB";

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Autowired
    private PedidoItemService pedidoItemService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPedidoItemMockMvc;

    private PedidoItem pedidoItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PedidoItem createEntity(EntityManager em) {
        PedidoItem pedidoItem = new PedidoItem()
            .quantidade(DEFAULT_QUANTIDADE)
            .valorDoItem(DEFAULT_VALOR_DO_ITEM)
            .valorTotal(DEFAULT_VALOR_TOTAL)
            .observacaoo(DEFAULT_OBSERVACAOO);
        return pedidoItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PedidoItem createUpdatedEntity(EntityManager em) {
        PedidoItem pedidoItem = new PedidoItem()
            .quantidade(UPDATED_QUANTIDADE)
            .valorDoItem(UPDATED_VALOR_DO_ITEM)
            .valorTotal(UPDATED_VALOR_TOTAL)
            .observacaoo(UPDATED_OBSERVACAOO);
        return pedidoItem;
    }

    @BeforeEach
    public void initTest() {
        pedidoItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createPedidoItem() throws Exception {
        int databaseSizeBeforeCreate = pedidoItemRepository.findAll().size();
        // Create the PedidoItem
        restPedidoItemMockMvc.perform(post("/api/pedido-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pedidoItem)))
            .andExpect(status().isCreated());

        // Validate the PedidoItem in the database
        List<PedidoItem> pedidoItemList = pedidoItemRepository.findAll();
        assertThat(pedidoItemList).hasSize(databaseSizeBeforeCreate + 1);
        PedidoItem testPedidoItem = pedidoItemList.get(pedidoItemList.size() - 1);
        assertThat(testPedidoItem.getQuantidade()).isEqualTo(DEFAULT_QUANTIDADE);
        assertThat(testPedidoItem.getValorDoItem()).isEqualTo(DEFAULT_VALOR_DO_ITEM);
        assertThat(testPedidoItem.getValorTotal()).isEqualTo(DEFAULT_VALOR_TOTAL);
        assertThat(testPedidoItem.getObservacaoo()).isEqualTo(DEFAULT_OBSERVACAOO);
    }

    @Test
    @Transactional
    public void createPedidoItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pedidoItemRepository.findAll().size();

        // Create the PedidoItem with an existing ID
        pedidoItem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPedidoItemMockMvc.perform(post("/api/pedido-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pedidoItem)))
            .andExpect(status().isBadRequest());

        // Validate the PedidoItem in the database
        List<PedidoItem> pedidoItemList = pedidoItemRepository.findAll();
        assertThat(pedidoItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPedidoItems() throws Exception {
        // Initialize the database
        pedidoItemRepository.saveAndFlush(pedidoItem);

        // Get all the pedidoItemList
        restPedidoItemMockMvc.perform(get("/api/pedido-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pedidoItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantidade").value(hasItem(DEFAULT_QUANTIDADE)))
            .andExpect(jsonPath("$.[*].valorDoItem").value(hasItem(DEFAULT_VALOR_DO_ITEM)))
            .andExpect(jsonPath("$.[*].valorTotal").value(hasItem(DEFAULT_VALOR_TOTAL)))
            .andExpect(jsonPath("$.[*].observacaoo").value(hasItem(DEFAULT_OBSERVACAOO)));
    }
    
    @Test
    @Transactional
    public void getPedidoItem() throws Exception {
        // Initialize the database
        pedidoItemRepository.saveAndFlush(pedidoItem);

        // Get the pedidoItem
        restPedidoItemMockMvc.perform(get("/api/pedido-items/{id}", pedidoItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pedidoItem.getId().intValue()))
            .andExpect(jsonPath("$.quantidade").value(DEFAULT_QUANTIDADE))
            .andExpect(jsonPath("$.valorDoItem").value(DEFAULT_VALOR_DO_ITEM))
            .andExpect(jsonPath("$.valorTotal").value(DEFAULT_VALOR_TOTAL))
            .andExpect(jsonPath("$.observacaoo").value(DEFAULT_OBSERVACAOO));
    }
    @Test
    @Transactional
    public void getNonExistingPedidoItem() throws Exception {
        // Get the pedidoItem
        restPedidoItemMockMvc.perform(get("/api/pedido-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePedidoItem() throws Exception {
        // Initialize the database
        pedidoItemService.save(pedidoItem);

        int databaseSizeBeforeUpdate = pedidoItemRepository.findAll().size();

        // Update the pedidoItem
        PedidoItem updatedPedidoItem = pedidoItemRepository.findById(pedidoItem.getId()).get();
        // Disconnect from session so that the updates on updatedPedidoItem are not directly saved in db
        em.detach(updatedPedidoItem);
        updatedPedidoItem
            .quantidade(UPDATED_QUANTIDADE)
            .valorDoItem(UPDATED_VALOR_DO_ITEM)
            .valorTotal(UPDATED_VALOR_TOTAL)
            .observacaoo(UPDATED_OBSERVACAOO);

        restPedidoItemMockMvc.perform(put("/api/pedido-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPedidoItem)))
            .andExpect(status().isOk());

        // Validate the PedidoItem in the database
        List<PedidoItem> pedidoItemList = pedidoItemRepository.findAll();
        assertThat(pedidoItemList).hasSize(databaseSizeBeforeUpdate);
        PedidoItem testPedidoItem = pedidoItemList.get(pedidoItemList.size() - 1);
        assertThat(testPedidoItem.getQuantidade()).isEqualTo(UPDATED_QUANTIDADE);
        assertThat(testPedidoItem.getValorDoItem()).isEqualTo(UPDATED_VALOR_DO_ITEM);
        assertThat(testPedidoItem.getValorTotal()).isEqualTo(UPDATED_VALOR_TOTAL);
        assertThat(testPedidoItem.getObservacaoo()).isEqualTo(UPDATED_OBSERVACAOO);
    }

    @Test
    @Transactional
    public void updateNonExistingPedidoItem() throws Exception {
        int databaseSizeBeforeUpdate = pedidoItemRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPedidoItemMockMvc.perform(put("/api/pedido-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pedidoItem)))
            .andExpect(status().isBadRequest());

        // Validate the PedidoItem in the database
        List<PedidoItem> pedidoItemList = pedidoItemRepository.findAll();
        assertThat(pedidoItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePedidoItem() throws Exception {
        // Initialize the database
        pedidoItemService.save(pedidoItem);

        int databaseSizeBeforeDelete = pedidoItemRepository.findAll().size();

        // Delete the pedidoItem
        restPedidoItemMockMvc.perform(delete("/api/pedido-items/{id}", pedidoItem.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PedidoItem> pedidoItemList = pedidoItemRepository.findAll();
        assertThat(pedidoItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
