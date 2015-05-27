package controllers;

import java.util.*;

import dao.CategoriaDao;
import models.Categoria;
import models.Fornecedor;
import models.Produto;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.produto.form;
import views.html.produto.index;
import dao.FornecedorDao;
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

        List<Fornecedor> fornecedores = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();
        FornecedorDao fornecedorDao = new FornecedorDao();
        CategoriaDao categoriaDao = new CategoriaDao();

        try {
            fornecedorDao.begin();
            fornecedores = fornecedorDao.listaFornecedor();
            categorias = categoriaDao.todos();
            fornecedorDao.commit();

        } catch (Exception e) {
            if (fornecedorDao.isConnected()) {
                fornecedorDao.rollback();
            }

            flash("error", "Erro ao listar fornecedores.");
        }

        return ok(form.render(produtoForm, fornecedores, categorias, new ArrayList<>()));
    }

    public static Result editar(Integer id) {
        ProdutoDao dao = new ProdutoDao();
        List<Fornecedor> fornecedores = new ArrayList<>();
        List<Categoria> categorias = new ArrayList<>();
        List<Categoria> categoriasProduto = new ArrayList<>();
        Form<Produto> produtoForm = Form.form(Produto.class);
        dao.begin();

        try {
            Produto produto = dao.consultarProduto(id);
            produtoForm = produtoForm.fill(produto);

            FornecedorDao fornecedorDao = new FornecedorDao();
            CategoriaDao categoriaDao = new CategoriaDao();

            fornecedores = fornecedorDao.listaFornecedor();
            categorias = categoriaDao.todos();
            categoriasProduto = produto.getCategorias();

            dao.commit();
        } catch (Exception e) {

            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Erro ao listar fornecedores.");
        }

        return ok(form.render(produtoForm, fornecedores, categorias, categoriasProduto));
    }

    public static Result save() {
        Produto produto = Form.form(Produto.class).bindFromRequest().get();
        ProdutoDao dao = new ProdutoDao();
        FornecedorDao fornecedorDao = new FornecedorDao();
        Integer idFornecedor = Integer.parseInt(Form.form(Produto.class).bindFromRequest().field("id_fornecedor").value());
        Map<String, String[]> formUrlEncoded = request().body().asFormUrlEncoded();

        List<Categoria> categorias = new ArrayList<>();

        dao.begin();

        try {
            produto.setFornecedor(fornecedorDao.consultarFornecedor(idFornecedor));

            for (String key : formUrlEncoded.keySet()) {
                String[] values = formUrlEncoded.get(key);
                for (String val : values) {
                    if ("id_categoria".equals(key)) categorias.add(new Categoria(Integer.valueOf(val)));
                }
            }

            produto.setCategorias(categorias);

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

            return ok(form.render(produtoForm, new ArrayList<>(),  new ArrayList<>(), new ArrayList<>()));
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