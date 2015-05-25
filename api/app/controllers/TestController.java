package controllers;
import dao.ClienteDao;
import dao.ProdutoDao;
import factory.SessionHibernateFactory;
import models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.test.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;


public class TestController extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result carga() {
        ClienteDao dao = new ClienteDao();

        try {
            dao.begin();

            Endereco endereco = new Endereco();
            endereco.setCep("61900340");
            endereco.setCidade("Maracanaú");
            endereco.setUf("CE");
            endereco.setLogradouro("Rua cinco");
            endereco.setNumero(503);

            dao.salvar(endereco);

            for (int i = 1; i<100; i++) {
                Cliente cliente = new Cliente();
                cliente.setCpf("12345567"+i);
                cliente.setDataNascimento(new Date());
                cliente.setEndereco(endereco);
                cliente.setNome("Cliente "+i);


                dao.salvar(cliente);

                Vendedor vendedor = new Vendedor();
                vendedor.setCpf("098978776"+i);
                vendedor.setNome("Vendedor "+i);
                vendedor.setDataNascimento(new Date());
                vendedor.setEndereco(endereco);

                dao.salvar(vendedor);

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setEndereco(endereco);
                fornecedor.setCnpj("34798375/100"+i);
                fornecedor.setNome("Fornecedor "+i);
                fornecedor.setNomeFantasia("Fornecedor Fantasia "+i);

                dao.salvar(fornecedor);

                Produto produto = new Produto();
                produto.setNome("Produto "+i);
                produto.setPreco(10.1*i);
                produto.setEstoque(3*i);
                produto.setFornecedor(fornecedor);

                dao.salvar(produto);

                Pedido pedido = new Pedido();
                pedido.setCliente(cliente);
                pedido.setVendedor(vendedor);

                dao.salvar(pedido);

                ItensPedido itens = new ItensPedido();
                itens.setPedido(pedido);
                itens.setProduto(produto);
                itens.setQuantidade(i);

                dao.salvar(itens);

                if (i % 50 == 0) {
                    Agenda agenda = new Agenda();
                    agenda.setCliente(cliente);
                    agenda.setVendedor(vendedor);
                    agenda.setData(new Date());

                    dao.salvar(agenda);
                }
            }

            dao.commit();


            flash("success", "Carga inicial finalizada com sucesso. ");
        } catch (Exception e) {
            if (dao.getSession().isOpen()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro na carga inicial : " + e.getMessage());
        }
        return ok(carga.render());
    }

    public static Result pool() {
        try {
//            for (int i = 0; i < 5; i++) {
//                (new Thread(){
//                    public void run() {
//                        while(true){
//                            try {
//
//                            } catch (Exception e) {
//
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//                ).start();
//            }

            flash("success", "Teste de pool de conexões finalizado com sucesso.");
        } catch (Exception e) {
            flash("error", "Ocorreu um erro ao testar pool de conexões: " + e.getMessage());
        }
        return ok(pool.render());
    }

    public static Result cache() {
        ProdutoDao dao = new ProdutoDao();
        Connection conn;

        String resultado1 = "";
        String resultado2 = "";

        try {
            Session session = SessionHibernateFactory.getHibernateSession();
            Transaction transaction = session.beginTransaction();
            System.out.println("CONN 1 [ABERTA]");

            System.out.println("CONN 1 [CONSULTANDO PRODUTO]");
            Produto produto = (Produto) session.get(Produto.class, new Integer(1));
            resultado1 = produto.getNome();
            System.out.println("CONN 1 [RESULTADO]: " + resultado1);


            System.out.println("Abrindo conexao paralela");
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/trabalho-hibernate?autoReconnect=true",
                    "root",
                    "1234"
            );

            Statement stmt = conn.createStatement();
            StringBuilder consulta = new StringBuilder();

            consulta.append("update produto set nome = '");
            consulta.append("Produto ");
            consulta.append((new Date()).toString());
            consulta.append("'");
            consulta.append(" where id_produto = 1");

            stmt.executeUpdate(consulta.toString());
            System.out.println("Produto atualizado em outra conexão.");

            transaction.commit();
            System.out.println("CONN 1 [Finalizada].");


            session = SessionHibernateFactory.getHibernateSession();
            transaction = session.beginTransaction();
            System.out.println("CONN 2 [Aberta].");

            System.out.println("CONN 2 [CONSULTANDO PRODUTO]");
            produto = (Produto) session.get(Produto.class, new Integer(1));
            resultado2 = produto.getNome();
            System.out.println("CONN 2 [RESULTADO]: " + resultado1);

            transaction.commit();

            flash("success", "Teste de cache finalizado com sucesso.");
        } catch (Exception e) {
            if (dao.isConnected()) {
                dao.rollback();
            }

            flash("error", "Ocorreu um erro ao testar cache: " + e.getMessage());
        }

        return ok(cache.render(resultado1, resultado2));
    }

}