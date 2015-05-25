package controllers;

import java.util.List;

import models.ItensPedido;
import models.Pedido;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.pedido.itens;
import dao.ItensPedidoDao;
import dao.PedidoDao;

public class ItensPedidoController extends Controller {
	
	public static Result index(Integer id) {
		ItensPedidoDao dao = new ItensPedidoDao();
		dao.begin();

		try {
			PedidoDao pedidoDao = new PedidoDao();
			Pedido pedido = pedidoDao.consultarPedido(id);
			
			System.out.println(pedido.getCliente().getNome());
			
			List<ItensPedido> itensPedido = dao.listarItensPedido(id);
			
			
			//ClienteDao clienteDao = new ClienteDao();
			//Cliente cliente = clienteDao.consultarCliente(id);
			
			for(ItensPedido i: itensPedido){
				i.setTotal();
			}
			
			dao.commit();
			
			return ok(itens.render(itensPedido, pedido.getCliente(), id));

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
