package dao;

import java.util.List;

import models.ItensPedido;

import org.hibernate.FetchMode;

public class ItensPedidoDao extends GenericDao {
	
	public ItensPedidoDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<ItensPedido> listarItensPedido(Integer id) {
		return super.session.createCriteria(ItensPedido.class)
				.setFetchMode("produto", FetchMode.JOIN).list();
	}
	
}
