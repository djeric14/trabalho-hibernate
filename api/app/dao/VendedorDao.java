package dao;

import java.util.List;

import models.Vendedor;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

public class VendedorDao extends GenericDao {

	public VendedorDao() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Vendedor> todos() {
		return super.session.createCriteria(Vendedor.class).setFetchMode("endereco", FetchMode.JOIN).list();
	}
	
	public Vendedor consultarVendedor(Integer id) throws Exception {
		return (Vendedor) super.session.createCriteria(Vendedor.class)
				  				.add(Restrictions.eq("id", id))
								.setFetchMode("endereco", FetchMode.JOIN)
								.uniqueResult();
    }
	
	@SuppressWarnings("unchecked")
	public List<Vendedor> consultarVendedoresPorNome(String nome) {
		String consulta = "from Vendedor v where v.nome like :nome";
		List<Vendedor> vendedores = super.session.createQuery(consulta).setParameter("nome", "%"+nome+"%").list(); 
		return vendedores;
	}
	
	public Vendedor consultarPessoaCPF(String cpf) throws Exception {
    	String consulta = "from Vendedor v where v.cpf = :cpf";
    	return (Vendedor)super.session.createQuery(consulta).setParameter("cpf", cpf).uniqueResult();
	}
}
