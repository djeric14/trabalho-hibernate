package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "vendedor")
@PrimaryKeyJoinColumn(name="id_pessoa")
public class Vendedor extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6621094817508679486L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vendedor")
	private Integer id;
	
	@Column(name = "comissao")
	@Basic(optional = true)
	private Double comissao = 0.10;

	@Column(name = "total_vendas")
	@Basic(optional = true)
	private Integer totalVendas = 0;
	
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedor", fetch=FetchType.LAZY)
	// private List<Agenda> agendaVendedor;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}

	public Integer getTotalVendas() {
		return totalVendas;
	}

	public void setTotalVendas(Integer totalVendas) {
		this.totalVendas = totalVendas;
	}

	// public List<Agenda> getAgendaVendedor() {
	// 	return agendaVendedor;
	// }

	// public void setAgendaVendedor(List<Agenda> agendaVendedor) {
	// 	this.agendaVendedor = agendaVendedor;
	// }
}
