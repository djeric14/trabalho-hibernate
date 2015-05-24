package controllers;

import java.util.List;

import models.Pedido;
import play.mvc.Controller;
import play.mvc.Result;
import dao.PedidoDao;
import views.html.pedido.index;

public class PedidoController extends Controller {
	
	public static Result index(Integer id) {
		PedidoDao dao = new PedidoDao();
		dao.begin();

		try {
			List<Pedido> clientePedidos = dao.pedidosCliente(id);
			dao.commit();

			return ok(index.render(clientePedidos));

		} catch (Exception e) {
			if (dao.isConnected()) {
				dao.rollback();
			}

			flash("error",
					"Ocorreu um erro : " + e.getMessage());
		}

		return redirect(routes.ClienteController.index());
	}

}
