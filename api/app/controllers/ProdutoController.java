package controllers;

import dao.FornecedorDao;
import dao.ProdutoDao;
import models.Fornecedor;
import models.Produto;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.produto.form;
import views.html.produto.index;

import java.util.ArrayList;
import java.util.List;

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

        List<Fornecedor> fornecedores = new ArrayList<>();
        FornecedorDao fornecedorDao = new FornecedorDao();

        try {
            fornecedorDao.begin();
            fornecedores = fornecedorDao.listaFornecedor();
            fornecedorDao.commit();

        } catch (Exception e) {
            if (fornecedorDao.isConnected()) {
                fornecedorDao.rollback();
            }

            flash("error", "Erro ao listar fornecedores.");
        }

        return ok(form.render(produtoForm, fornecedores));
    }

    public static Result editar(Integer id) {
        ProdutoDao dao = new ProdutoDao();
        List<Fornecedor> fornecedores = new ArrayList<>();
        Form<Produto> produtoForm = Form.form(Produto.class);
        dao.begin();

        try {
            produtoForm = produtoForm.fill(
                    dao.consultarProduto(id)
            );

            FornecedorDao fornecedorDao = new FornecedorDao();
            fornecedores = fornecedorDao.listaFornecedor();

            dao.commit();
        } catch (Exception e) {

            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Erro ao listar fornecedores.");
        }

        return ok(form.render(produtoForm, fornecedores));
    }

    public static Result save() {
        Produto produto = Form.form(Produto.class).bindFromRequest().get();
        ProdutoDao dao = new ProdutoDao();
        FornecedorDao fornecedorDao = new FornecedorDao();
        Integer idFornecedor = Integer.parseInt(Form.form(Produto.class).bindFromRequest().field("id_fornecedor").value());

        dao.begin();

        try {
            produto.setFornecedor(fornecedorDao.consultarFornecedor(idFornecedor));
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

            return ok(form.render(produtoForm, new ArrayList<>()));
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