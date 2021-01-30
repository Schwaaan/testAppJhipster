package com.my.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PedidoItem.
 */
@Entity
@Table(name = "pedido_item")
public class PedidoItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade")
    private String quantidade;

    @Column(name = "valor_do_item")
    private String valorDoItem;

    @Column(name = "valor_total")
    private String valorTotal;

    @Column(name = "observacaoo")
    private String observacaoo;

    @OneToOne
    @JoinColumn(unique = true)
    private Produto produto;

    @OneToOne
    @JoinColumn(unique = true)
    private Usuario cliente;

    @ManyToOne
    @JsonIgnoreProperties(value = "pedidoItems", allowSetters = true)
    private Pedido pedido;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public PedidoItem quantidade(String quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getValorDoItem() {
        return valorDoItem;
    }

    public PedidoItem valorDoItem(String valorDoItem) {
        this.valorDoItem = valorDoItem;
        return this;
    }

    public void setValorDoItem(String valorDoItem) {
        this.valorDoItem = valorDoItem;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public PedidoItem valorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObservacaoo() {
        return observacaoo;
    }

    public PedidoItem observacaoo(String observacaoo) {
        this.observacaoo = observacaoo;
        return this;
    }

    public void setObservacaoo(String observacaoo) {
        this.observacaoo = observacaoo;
    }

    public Produto getProduto() {
        return produto;
    }

    public PedidoItem produto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public PedidoItem cliente(Usuario usuario) {
        this.cliente = usuario;
        return this;
    }

    public void setCliente(Usuario usuario) {
        this.cliente = usuario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public PedidoItem pedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PedidoItem)) {
            return false;
        }
        return id != null && id.equals(((PedidoItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PedidoItem{" +
            "id=" + getId() +
            ", quantidade='" + getQuantidade() + "'" +
            ", valorDoItem='" + getValorDoItem() + "'" +
            ", valorTotal='" + getValorTotal() + "'" +
            ", observacaoo='" + getObservacaoo() + "'" +
            "}";
    }
}
