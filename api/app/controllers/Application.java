package controllers;

import play.*;
import play.mvc.*;
import play.db.jpa.JPA;
import models.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import views.html.*;

public class Application extends Controller {

	private static EntityManager em = null;

    public static Result index() {
    	em = (EntityManager) Persistence.createEntityManagerFactory("defaultPersistenceUnit").createEntityManager();

    	// Inicia uma transação
		em.getTransaction().begin();

		// ------------ Cria o primeiro Aluno ---------
		Aluno a1 = new Aluno();

		a1.setNome("João");

		em.persist(a1);
		em.getTransaction().commit();

        return ok(index.render("My first App with Play!"));
    }

}
