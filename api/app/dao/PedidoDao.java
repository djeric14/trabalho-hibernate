package dao;

import java.util.List;

import models.Pedido;

import org.hibernate.FetchMode;

public class PedidoDao extends GenericDao {

	public PedidoDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> pedidosCliente(Integer id) {
		return super.session.createCriteria(Pedido.class)
				.setFetchMode("vendedor", FetchMode.JOIN)
				.setFetchMode("cliente", FetchMode.JOIN).list();
	}
}
