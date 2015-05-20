package controllers;

import javax.persistence.EntityManager;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

	private static EntityManager em = null;

    public static Result index() {
    	// em = (EntityManager) Persistence.createEntityManagerFactory("defaultPersistenceUnit").createEntityManager();

    	// Inicia uma transação
		// em.getTransaction().begin();

		// ------------ Cria o primeiro Aluno ---------
		// Aluno a1 = new Aluno();

		// a1.setNome("João");

		// em.persist(a1);
		// em.getTransaction().commit();

        return ok(index.render("My first App with Play!"));
    }

}
