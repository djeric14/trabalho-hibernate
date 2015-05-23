package dao;

import java.util.List;

import models.Produto;

public class ProdutoDao extends GenericDao {

	public ProdutoDao() {
		super();
	}
	
	public Produto consultarProduto(Integer id) throws Exception{
    	String consulta = "from Produto p where p.id = :id";
    	return (Produto)super.session.createQuery(consulta).setParameter("id", id).uniqueResult();
    }
	
	@SuppressWarnings("unchecked")
	public List<Produto> consultarProdutosPorNome(String nome){
		String consulta = "from Produto p where p.nome like :nome";
		List<Produto> produtos = super.session.createQuery(consulta).setParameter("nome", "%"+nome+"%").list(); 
		return produtos;
	}
}
