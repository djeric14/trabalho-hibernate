package dao;

import java.util.List;

import models.BaseModel;

import org.hibernate.Session;

import factory.SessionHibernateFactory;

public abstract class GenericDao {
	protected final Session session;

	public GenericDao() {
		this.session = SessionHibernateFactory.getHibernateSession();
	}

	public void begin() {
		session.beginTransaction();
	}

	public void commit() {
		session.getTransaction().commit();
	}

	public void salvar(BaseModel base) {
		session.saveOrUpdate(base);
	}

	public void deletar(BaseModel base) {
		session.delete(base);
	}

	public void close() {
		session.close();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BaseModel> listarTodos(Class base){
    	return session.createQuery("from "+base.getName()).list();
    }
}
