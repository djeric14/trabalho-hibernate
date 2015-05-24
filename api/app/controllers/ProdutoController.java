package controllers;

import java.util.List;

import models.Produto;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.produto.form;
import views.html.produto.index;
import dao.ProdutoDao;

public class ProdutoController extends Controller {

    public static Result index() {
        ProdutoDao dao = new ProdutoDao();
        dao.begin();

        try {
            List<Produto> produtos = dao.todos();
            dao.commit();
            return ok(index.render(produtos));
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            return internalServerError(e.getMessage());
        }
    }

    public static Result adicionar() {
        Produto produto = new Produto();

        Form<Produto> produtoForm = Form.form(Produto.class).fill(produto);
//        FornecedorDao fornecedorDao = new FornecedorDao();


        return ok(form.render(produtoForm));
    }

    public static Result editar(Integer id) {
        ProdutoDao dao = new ProdutoDao();
        dao.begin();

        try {

            Form<Produto> produtoForm = Form.form(Produto.class).fill(
                    dao.consultarProduto(id)
            );

            dao.commit();
            return ok(form.render(produtoForm));
        } catch (Exception e) {

            if (dao.isConnected()) {
                dao.rollback();
            }

            return internalServerError(e.getMessage());
        }
    }

    public static Result save() {
        Produto produto = Form.form(Produto.class).bindFromRequest().get();
        ProdutoDao dao = new ProdutoDao();
        dao.begin();

        try {
            dao.salvar(produto);
            dao.commit();

            flash("success", "Produto salvo com sucesso.");
            return redirect(routes.ProdutoController.index());
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao tentar salvar: " + e.getMessage());

            Form<Produto> produtoForm = Form.form(Produto.class).fill(produto);
            return ok(form.render(produtoForm));
        }
    }

    public static Result deletar(Integer id) {
        ProdutoDao dao = new ProdutoDao();
        dao.begin();

        try {

            Produto produto = dao.consultarProduto(id);
            dao.deletar(produto);
            dao.commit();

            flash("success", "Produto removido com sucesso.");

        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao tentar remover: " + e.getMessage());
        }

        return redirect(routes.ProdutoController.index());
    }

}