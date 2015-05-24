package dao;

import models.Agenda;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class AgendaDao extends GenericDao {

	public AgendaDao() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Agenda> todos() {
		return super.session.createCriteria(Agenda.class)
					.setFetchMode("vendedor", FetchMode.JOIN)
					.setFetchMode("cliente", FetchMode.JOIN)
					.list();
	}

	public Agenda consultarAgenda(Integer id) throws Exception {
		return (Agenda) super.session.createCriteria(Agenda.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	
}
