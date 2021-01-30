package com.my.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.my.test.web.rest.TestUtil;

public class PedidoItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PedidoItem.class);
        PedidoItem pedidoItem1 = new PedidoItem();
        pedidoItem1.setId(1L);
        PedidoItem pedidoItem2 = new PedidoItem();
        pedidoItem2.setId(pedidoItem1.getId());
        assertThat(pedidoItem1).isEqualTo(pedidoItem2);
        pedidoItem2.setId(2L);
        assertThat(pedidoItem1).isNotEqualTo(pedidoItem2);
        pedidoItem1.setId(null);
        assertThat(pedidoItem1).isNotEqualTo(pedidoItem2);
    }
}
