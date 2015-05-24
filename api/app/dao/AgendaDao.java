package dao;

import java.util.List;

import models.Agenda;

import org.hibernate.criterion.Restrictions;

public class AgendaDao extends GenericDao {

	public AgendaDao() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Agenda> todos() {
		return super.session.createCriteria(Agenda.class).list();
	}

	public Agenda consultarAgenda(Integer id) throws Exception {
		return (Agenda) super.session.createCriteria(Agenda.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	
}
