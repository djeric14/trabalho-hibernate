package dao;

import java.util.List;

import models.Vendedor;

public class VendedorDao extends GenericDao {

	public VendedorDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Vendedor consultarVendedor(Integer id) throws Exception{
    	String consulta = "from Vendedor v where v.id = :id";
    	return (Vendedor)super.session.createQuery(consulta).setParameter("id", id).uniqueResult();
    }
	
	@SuppressWarnings("unchecked")
	public List<Vendedor> consultarVendedoresPorNome(String nome){
		String consulta = "from Vendedor v where v.nome like :nome";
		List<Vendedor> vendedores = super.session.createQuery(consulta).setParameter("nome", "%"+nome+"%").list(); 
		return vendedores;
	}
	
	public Vendedor consultarPessoaCPF(String cpf) throws Exception{
    	String consulta = "from Vendedor v where v.cpf = :cpf";
    	return (Vendedor)super.session.createQuery(consulta).setParameter("cpf", cpf).uniqueResult();
	}	
}
