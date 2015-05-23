package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="agenda_id_seq")
@Table(name = "agenda")
public class Agenda extends BaseModel {

    private static final long serialVersionUID = 3322492327382107596L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id_agenda")
    @Basic(optional = false)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @Basic(optional = false)
    private Vendedor vendedor;

    @ManyToOne(fetch=FetchType.LAZY)
    @Basic(optional = false)
    private Cliente cliente;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data")
    @Basic(optional = false)
    protected Date data;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agenda", fetch=FetchType.LAZY)
    private List<Visita> visitasAgendadas;

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

	public List<Visita> getVisitasAgendadas() {
		return visitasAgendadas;
	}

	public void setVisitasAgendadas(List<Visita> visitasAgendadas) {
		this.visitasAgendadas = visitasAgendadas;
	}
}