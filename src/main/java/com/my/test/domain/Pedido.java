package com.my.test.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Pedido.
 */
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @NotNull
    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "endereco")
    private String endereco;

    @NotNull
    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @OneToMany(mappedBy = "pedido")
    private Set<PedidoItem> pedidoItems = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public Pedido uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getData() {
        return data;
    }

    public Pedido data(String data) {
        this.data = data;
        return this;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public Pedido endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public Pedido ativo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Set<PedidoItem> getPedidoItems() {
        return pedidoItems;
    }

    public Pedido pedidoItems(Set<PedidoItem> pedidoItems) {
        this.pedidoItems = pedidoItems;
        return this;
    }

    public Pedido addPedidoItem(PedidoItem pedidoItem) {
        this.pedidoItems.add(pedidoItem);
        pedidoItem.setPedido(this);
        return this;
    }

    public Pedido removePedidoItem(PedidoItem pedidoItem) {
        this.pedidoItems.remove(pedidoItem);
        pedidoItem.setPedido(null);
        return this;
    }

    public void setPedidoItems(Set<PedidoItem> pedidoItems) {
        this.pedidoItems = pedidoItems;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pedido)) {
            return false;
        }
        return id != null && id.equals(((Pedido) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pedido{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", data='" + getData() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", ativo='" + isAtivo() + "'" +
            "}";
    }
}
