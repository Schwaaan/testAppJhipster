package com.my.test.service;

import com.my.test.domain.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Usuario}.
 */
public interface UsuarioService {

    /**
     * Save a usuario.
     *
     * @param usuario the entity to save.
     * @return the persisted entity.
     */
    Usuario save(Usuario usuario);

    /**
     * Get all the usuarios.
     *
     * @return the list of entities.
     */
    List<Usuario> findAll();


    /**
     * Get the "id" usuario.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Usuario> findOne(Long id);

    /**
     * Delete the "id" usuario.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
