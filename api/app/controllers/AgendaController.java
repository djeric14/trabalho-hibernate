package controllers;

import dao.AgendaDao;
import dao.ClienteDao;
import dao.VendedorDao;
import models.Agenda;
import models.Cliente;
import models.Vendedor;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.agenda.form;
import views.html.agenda.index;

import java.util.ArrayList;
import java.util.List;

public class AgendaController extends Controller {

    public static Result index() {
        AgendaDao dao = new AgendaDao();
        dao.begin();

        try {
            List<Agenda> agendas = dao.todos();
            dao.commit();
            return ok(index.render(agendas));
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            return internalServerError(e.getMessage());
        }
    }

    public static Result adicionar() {
        Agenda agenda = new Agenda();

        ClienteDao clienteDao = new ClienteDao();
        VendedorDao vendedorDao = new VendedorDao();

        List<Vendedor> vendedores = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();

        Form<Agenda> agendaForm = Form.form(Agenda.class).fill(agenda);

        try {
            vendedorDao.begin();
            vendedores = vendedorDao.todos();
            clientes = clienteDao.todos();

            vendedorDao.commit();
        } catch (Exception e) {
            if (vendedorDao.isConnected()) {
                vendedorDao.rollback();
            }

            flash("error", "Ocorreu um erro ao carregar a lista de vendedores e/ou clientes: " + e.getMessage());
        }

        return ok(form.render(agendaForm, vendedores, clientes));
    }

    public static Result editar(Integer id) {
        AgendaDao dao = new AgendaDao();
        ClienteDao clienteDao = new ClienteDao();
        VendedorDao vendedorDao = new VendedorDao();

        List<Vendedor> vendedores = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();

        Form<Agenda> agendaForm = Form.form(Agenda.class);

        dao.begin();

        try {
            vendedores = vendedorDao.todos();
            clientes = clienteDao.todos();

            agendaForm = agendaForm.fill(
                    dao.consultarAgenda(id)
            );

            dao.commit();
        } catch (Exception e) {

            if (dao.isConnected()) {
                dao.rollback();
            }
        }

        return ok(form.render(agendaForm, vendedores, clientes));
    }

    public static Result save() {
        Form<Agenda> agendaForm = Form.form(Agenda.class);
        Agenda agenda = agendaForm.bindFromRequest().get();
        AgendaDao dao = new AgendaDao();

        ClienteDao clienteDao = new ClienteDao();
        VendedorDao vendedorDao = new VendedorDao();

        List<Vendedor> vendedores = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();

        dao.begin();

        try {
            vendedores = vendedorDao.todos();
            clientes = clienteDao.todos();

            dao.salvar(agenda);
            dao.commit();

            flash("success", "Agenda salvo com sucesso.");
            return redirect(routes.AgendaController.index());
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao tentar salvar: " + e.getMessage());

            agendaForm = Form.form(Agenda.class).fill(agenda);
            return ok(form.render(agendaForm, vendedores, clientes));
        }
    }

    public static Result deletar(Integer id) {
        AgendaDao dao = new AgendaDao();
        dao.begin();

        try {

            Agenda agenda = dao.consultarAgenda(id);
            dao.deletar(agenda);
            dao.commit();

            flash("success", "Agenda removido com sucesso.");

        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao tentar remover: " + e.getMessage());
        }

        return redirect(routes.AgendaController.index());
    }

}