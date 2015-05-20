import java.util.Calendar;

import models.Cliente;
import models.Endereco;
import models.Pessoa;
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

		Pessoa pessoa = new Pessoa();

		pessoa.setCpf("111111111");

		Calendar dt = Calendar.getInstance();

		dt.set(Calendar.YEAR, 1983);
		dt.set(Calendar.MONTH, Calendar.AUGUST);
		dt.set(Calendar.DAY_OF_MONTH, 11);
		pessoa.setDataNascimento(dt.getTime());
		pessoa.setEndereco(endereco);
		pessoa.setNome("João");
		
		enderecoDao.salvar(pessoa);

		Pessoa pessoa2 = new Pessoa();

		pessoa2.setCpf("111111111");

		dt = Calendar.getInstance();

		dt.set(Calendar.YEAR, 1974);
		dt.set(Calendar.MONTH, Calendar.FEBRUARY);
		dt.set(Calendar.DAY_OF_MONTH, 14);
		pessoa2.setDataNascimento(dt.getTime());
		pessoa2.setEndereco(endereco2);
		pessoa2.setNome("Mario");
		
		enderecoDao.salvar(pessoa2);
		
		
		Cliente c = new Cliente();
		
		enderecoDao.salvar(c);
		

		enderecoDao.commit();
	}

}
