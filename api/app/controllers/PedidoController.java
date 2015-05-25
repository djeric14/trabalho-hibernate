package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Cliente;
import models.ItensPedido;
import models.Pedido;
import models.Vendedor;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.pedido.form;
import views.html.pedido.index;
import views.html.pedido.listar;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.VendedorDao;

public class PedidoController extends Controller {
	
	public static Result index(Integer id) {
		PedidoDao dao = new PedidoDao();
		dao.begin();

		try {
			ClienteDao clienteDao = new ClienteDao();
			Cliente cliente = clienteDao.consultarCliente(id);
			
			List<Pedido> clientePedidos = dao.pedidosCliente(id);
			
			dao.commit();
			
			return ok(index.render(clientePedidos, cliente));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (dao.isConnected()) {
				dao.rollback();
			}

			flash("error",
					"Ocorreu um erro : " + e.getMessage());
		}

		return redirect(routes.ClienteController.index());
	}
	
	public static Result adicionar(Integer id) {
        Pedido pedido = new Pedido();
        Form<Pedido> pedidoForm = Form.form(Pedido.class).fill(pedido);

        List<Vendedor> vendedores = new ArrayList<>();
        VendedorDao vendedorDao = new VendedorDao();
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = new Cliente();
      
        List<ItensPedido> itensPedido = new ArrayList<>();
        
        try {
            vendedorDao.begin();
            vendedores = vendedorDao.todos();
            cliente = clienteDao.consultarCliente(id);
            vendedorDao.commit();

        } catch (Exception e) {
            if (vendedorDao.isConnected()) {
            	vendedorDao.rollback();
            }

            flash("error", "Erro ao listar fornecedores.");
        }

        return ok(form.render(pedidoForm, cliente, vendedores, itensPedido));
    }
	
	public static Result todos(){
		PedidoDao dao = new PedidoDao();
		dao.begin();

		try {
			
			List<Pedido> clientePedidos = dao.todos();
			dao.commit();
			
			return ok(listar.render(clientePedidos));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (dao.isConnected()) {
				dao.rollback();
			}

			flash("error",
					"Ocorreu um erro : " + e.getMessage());
		}

		return redirect(routes.Application.index());
	}

}
