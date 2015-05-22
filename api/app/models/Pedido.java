package models;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="pedido_id_seq")
@Table(name = "pedido")
public class Pedido extends BaseModel {

    private static final long serialVersionUID = -1339717517200607306L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id_pedido")
    @Basic(optional = false)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @Basic(optional = false)
    private Vendedor vendedor;

    @ManyToOne(fetch=FetchType.LAZY)
    @Basic(optional = false)
    private Cliente cliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}