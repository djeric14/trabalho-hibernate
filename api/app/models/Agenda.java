package models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="agenda_id_seq")
@Table(name = "agenda")
public class Agenda implements Serializable {

    private static final long serialVersionUID = 3322492327382107596L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id_agenda")
    @Basic(optional = false)
    private Integer id;

    @Column(name = "id_vendedor")
    @Basic(optional = false)
    private Integer vendedor;

    @Column(name = "id_cliente")
    @Basic(optional = false)
    private Integer cliente;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data")
    @Basic(optional = false)
    protected Date data;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public Integer getVendedor() {
        return vendedor;
    }

    public void setVendedor(Integer vendedor) {
        this.vendedor = vendedor;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}