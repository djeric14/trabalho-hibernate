package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "SEQUENCE", sequenceName = "cliente_id_seq")
@PrimaryKeyJoinColumn(name="id_pessoa")
public class Vendedor extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6621094817508679486L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vendedor")
	private Integer id;

}
