import java.util.Calendar;

import models.Cliente;
import models.Endereco;
import models.Fornecedor;
import models.Pessoa;
import models.Produto;
import models.Vendedor;
import dao.EnderecoDao;

public class TesteHibernate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnderecoDao enderecoDao = new EnderecoDao();
		enderecoDao.begin();
		Endereco endereco = new Endereco();
		endereco.setLatitude(10);
		endereco.setLogradouro("Rua Felino Barroso");
		endereco.setLongitude(145);
		endereco.setNumero(1009);

		enderecoDao.salvar(endereco);

		Endereco endereco2 = new Endereco();
		endereco2.setLatitude(10);
		endereco2.setLogradouro("Rua Felino Barroso");
		endereco2.setLongitude(145);
		endereco2.setNumero(1009);

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
				

		enderecoDao.commit();
	}

}
