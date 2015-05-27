package dao;

import java.util.List;

import models.Produto;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

public class ProdutoDao extends GenericDao {

	public ProdutoDao() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Produto> todos() {
		return super.session.createCriteria(Produto.class).setFetchMode("fornecedor", FetchMode.JOIN).list();
	}

	public Produto consultarProduto(Integer id) throws Exception {
		return (Produto) super.session.createCriteria(Produto.class)
				.add(Restrictions.eq("id", id))
				.setFetchMode("fornecedor", FetchMode.JOIN)
				.setFetchMode("categorias", FetchMode.JOIN)
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> consultarProdutosPorNome(String nome){
		String consulta = "from Produto p where p.nome like :nome";
		List<Produto> produtos = super.session.createQuery(consulta).setParameter("nome", "%"+nome+"%").list(); 
		return produtos;
	}
}
