package controllers;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import models.Agenda;
import models.Fornecedor;
import models.Produto;
import models.Visita;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

	private static EntityManager em = null;

    public static Result index() {
    	em = (EntityManager) Persistence.createEntityManagerFactory("defaultPersistenceUnit").createEntityManager();

    	// Inicia uma transação
		em.getTransaction().begin();

		Agenda agenda = new Agenda();

        agenda.setCliente(1);
        agenda.setData(new Date());
        agenda.setVendedor(1);

        em.persist(agenda);

        Visita visita = new Visita();
        visita.setAgenda(agenda);
        visita.setHoraFim(new Date());
        visita.setHoraInicio(new Date());
        visita.setLatitude(1.1);
        visita.setLongitude(1.2);

        em.persist(visita);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpj("39823923");
        fornecedor.setNome("Fornecedor 1");
        fornecedor.setNomeFantasia("Nome Fantasia do Fornecedor");

        em.persist(fornecedor);

        Produto produto = new Produto();
        produto.setEstoque(10);
        produto.setFornecedor(fornecedor);
        produto.setNome("Produto 1");
        produto.setPreco(10.1);

        em.persist(produto);
        
        em.getTransaction().rollback();
//		em.getTransaction().commit();

        return ok(index.render("My first App with Play!"));
    }

}

