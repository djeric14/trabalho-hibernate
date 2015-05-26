import java.util.Calendar;
import java.util.List;

import models.Cliente;
import models.Endereco;
import models.Fornecedor;
import models.ItensPedido;
import models.Pedido;
import models.Pessoa;
import models.Produto;
import models.Vendedor;
import dao.EnderecoDao;
import dao.PedidoDao;

public class TesteHibernate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnderecoDao enderecoDao = new EnderecoDao();
		enderecoDao.begin();
	
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua Felino Barroso");
		endereco.setCidade("Fortaleza");
		endereco.setUf("CE");
		endereco.setNumero(1009);
		endereco.setCep("60050-130");

		enderecoDao.salvar(endereco);

		Endereco endereco2 = new Endereco();
		endereco2.setCidade("Fortaleza");
		endereco2.setUf("CE");
		endereco2.setLogradouro("Rua Felino Barroso");
		endereco2.setNumero(2000);
		endereco2.setCep("98888-130");

		enderecoDao.salvar(endereco2);

		Pessoa pessoa = new Cliente();
	

		pessoa.setCpf("111111111");

		Calendar dt = Calendar.getInstance();

		dt.set(Calendar.YEAR, 1983);
		dt.set(Calendar.MONTH, Calendar.AUGUST);
		dt.set(Calendar.DAY_OF_MONTH, 11);
		pessoa.setDataNascimento(dt.getTime());
		pessoa.setNome("João");
		pessoa.setEndereco(endereco2);
		
		enderecoDao.salvar(pessoa);

		Pessoa pessoa2 = new Vendedor();

		pessoa2.setCpf("222222222");

		dt = Calendar.getInstance();

		dt.set(Calendar.YEAR, 1974);
		dt.set(Calendar.MONTH, Calendar.FEBRUARY);
		dt.set(Calendar.DAY_OF_MONTH, 14);
		pessoa2.setDataNascimento(dt.getTime());
		pessoa2.setNome("Mario");
		pessoa2.setEndereco(endereco);
		
		enderecoDao.salvar(pessoa2);
		
		Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpj("39823923");
        fornecedor.setNome("Fornecedor 1");
        fornecedor.setNomeFantasia("Nome Fantasia do Fornecedor");
        fornecedor.setEndereco(endereco);

        enderecoDao.salvar(fornecedor);

        Produto produto = new Produto();
        produto.setEstoque(10);
        produto.setFornecedor(fornecedor);
        produto.setNome("Produto 1");
        produto.setPreco(10.1);
        
        enderecoDao.salvar(produto); 
		
		PedidoDao cDao = new PedidoDao();
		
		
		try {
			//System.out.println(pessoa.getId());
			//Pedido c = cDao.pedidosCliente(new Integer(1));
			
			List<Pedido> listP  = cDao.pedidosCliente(1);
			
			for(Pedido p: listP){
				System.out.println(p.getCliente().getNome());
				System.out.println(p.getCliente().getCpf());
				for(ItensPedido i: p.getItensPedido()){
					System.out.println("Produto="+i.getProduto().getNome());
					//System.out.println("Cliente="+i.gegetCliente().getNome());
				}
				
			}
					
			//List<Cliente> cList = cDao.consultarClientesPorNome("João");
			
			
			/**for(Cliente c1 :cList){
				System.out.println(c1.getNome());
				System.out.println(c1.getCpf());
			}*/
			
			Endereco e = enderecoDao.consultarEnderecoCep("60050-130", 1009);
			
		//	System.out.println(e.getLogradouro()+" - "+e.getLatitude()); */
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		enderecoDao.commit();
		

	}

}
