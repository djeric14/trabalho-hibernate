package controllers;

import java.util.Date;

import models.Agenda;
import models.Cliente;
import models.Endereco;
import models.Fornecedor;
import models.Produto;
import models.Vendedor;
import models.Visita;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import dao.PessoaDao;

public class Application extends Controller {


    public static Result index() {
        PessoaDao gd = new PessoaDao();
        gd.begin();
        
        Endereco endereco = new Endereco();
        endereco.setLatitude(1);
        endereco.setLongitude(1);
        endereco.setNumero(100);
        endereco.setLogradouro("Rua");

        gd.salvar(endereco);

        Cliente cliente = new Cliente();
        cliente.setCpf("34483765");
        cliente.setDataNascimento(new Date());
        cliente.setNome("Nome do Cliente");
        cliente.setEndereco(endereco);

        gd.salvar(cliente);

        Vendedor vendedor = new Vendedor();
        vendedor.setNome("Nome do Vendedor");
        vendedor.setCpf("3458495");
        vendedor.setDataNascimento(new Date());
        vendedor.setEndereco(endereco);

        gd.salvar(vendedor);

        Agenda agenda = new Agenda();

        agenda.setCliente(cliente);
        agenda.setData(new Date());
        agenda.setVendedor(vendedor);

        gd.salvar(agenda);

        Visita visita = new Visita();
        visita.setAgenda(agenda);
        visita.setHoraFim(new Date());
        visita.setHoraInicio(new Date());
        visita.setLatitude(1.1);
        visita.setLongitude(1.2);

        gd.salvar(visita);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpj("39823923");
        fornecedor.setNome("Fornecedor 1");
        fornecedor.setNomeFantasia("Nome Fantasia do Fornecedor");
        fornecedor.setEndereco(endereco);

        gd.salvar(fornecedor);

        Produto produto = new Produto();
        produto.setEstoque(10);
        produto.setFornecedor(fornecedor);
        produto.setNome("Produto 1");
        produto.setPreco(10.1);

        gd.salvar(produto);

        gd.commit();

        return ok(index.render("My first App with Play!"));
    }

}