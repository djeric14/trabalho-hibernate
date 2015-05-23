package models;

import java.util.List;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="cliente_id_seq")
@Table(name = "cliente")
public class Cliente extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4699449860340032136L;

	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
	@Column(name = "id_cliente")
	private Integer id;
	
	@Column(name = "preferencial", nullable = false)
	private Boolean preferencial = false;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch=FetchType.LAZY)
	private List<Pedido> pedidos;
	
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "agenda", fetch=FetchType.LAZY)
	// private List<Agenda> visitasAgendadasCliente;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getPreferencial() {
		return preferencial;
	}

	public void setPreferencial(Boolean preferencial) {
		this.preferencial = preferencial;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	// public List<Agenda> getVisitasAgendadasCliente() {
	// 	return visitasAgendadasCliente;
	// }

	// public void setVisitasAgendadasCliente(List<Agenda> visitasAgendadasCliente) {
	// 	this.visitasAgendadasCliente = visitasAgendadasCliente;
	// }
}
