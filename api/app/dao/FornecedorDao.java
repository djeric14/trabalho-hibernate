package dao;

import java.util.List;

import models.Fornecedor;

public class FornecedorDao extends GenericDao {

	public FornecedorDao() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> consultarFornecedores(String nome){
		String consulta = "from Fornecedor f where f.nome like :nome";
		List<Fornecedor> fornecedor = super.session.createQuery(consulta).setParameter("nome", "%"+nome+"%").list(); 
		return fornecedor;
	}
}
