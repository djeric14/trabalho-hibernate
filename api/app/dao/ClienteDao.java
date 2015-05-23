package dao;

import java.util.List;

import models.Cliente;

public class ClienteDao extends GenericDao {

	public ClienteDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cliente consultarCliente(Integer id) throws Exception{
    	String consulta = "from Cliente c where c.id = :id";
    	return (Cliente)super.session.createQuery(consulta).setParameter("id", id).uniqueResult();
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
