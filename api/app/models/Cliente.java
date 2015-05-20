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
public class Cliente extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4699449860340032136L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Integer id;

}
