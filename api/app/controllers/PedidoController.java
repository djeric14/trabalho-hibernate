package controllers;

import java.util.List;

import models.Cliente;
import models.Pedido;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.pedido.index;
import dao.ClienteDao;
import dao.PedidoDao;

public class PedidoController extends Controller {
	
	public static Result index(Integer id) {
		PedidoDao dao = new PedidoDao();
		dao.begin();

		try {
			ClienteDao clienteDao = new ClienteDao();
			Cliente cliente = clienteDao.consultarCliente(id);
			
			List<Pedido> clientePedidos = dao.pedidosCliente(id);
			
			dao.commit();
			
			return ok(index.render(clientePedidos, cliente.getNome()));

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

}
