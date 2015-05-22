package models;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="itens_pedido_id_seq")
@Table(name = "itens_pedido")
public class ItensPedido extends BaseModel {

    private static final long serialVersionUID = -1339717517200607306L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id_item")
    @Basic(optional = false)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @Basic(optional = false)
    private Pedido pedido;

    @ManyToOne(fetch=FetchType.LAZY)
    @Basic(optional = false)
    private Produto produto;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}