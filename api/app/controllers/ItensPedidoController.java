package controllers;

import java.util.List;

import models.Cliente;
import models.ItensPedido;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.pedido.itens;
import dao.ClienteDao;
import dao.ItensPedidoDao;

public class ItensPedidoController extends Controller {
	
	public static Result index(Integer id) {
		ItensPedidoDao dao = new ItensPedidoDao();
		dao.begin();

		try {
			List<ItensPedido> itensPedido = dao.listarItensPedido(id);
			
			ClienteDao clienteDao = new ClienteDao();
			Cliente cliente = clienteDao.consultarCliente(id);
			
			for(ItensPedido i: itensPedido){
				i.setTotal();
			}
			
			dao.commit();
			
			return ok(itens.render(itensPedido, cliente.getNome()));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (dao.isConnected()) {
				dao.rollback();
			}

			flash("error",
					"Ocorreu um erro : " + e.getMessage());
		}

		return redirect(routes.PedidoController.index(id));
	}
}
