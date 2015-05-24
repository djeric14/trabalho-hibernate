package dao;

import java.util.List;

import models.Cliente;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

public class ClienteDao extends GenericDao {

	public ClienteDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> todos() {
		return super.session.createCriteria(Cliente.class).setFetchMode("endereco", FetchMode.JOIN).list();
	}

	public Cliente consultarCliente(Integer id) throws Exception {
		return (Cliente) super.session.createCriteria(Cliente.class)
				.add(Restrictions.eq("id", id))
				.setFetchMode("endereco", FetchMode.JOIN)
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> consultarClientesPorNome(String nome){
		String consulta = "from Cliente c where c.nome like :nome";
		List<Cliente> clientes = super.session.createQuery(consulta).setParameter("nome", "%"+nome+"%").list(); 
		return clientes;
	}
	
	public Cliente consultarClienteCPF(String cpf) throws Exception{
    	String consulta = "from Cliente c where c.cpf = :cpf";
    	return (Cliente)super.session.createQuery(consulta).setParameter("cpf", cpf).uniqueResult();
    }
}
