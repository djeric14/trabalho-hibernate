package models;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="cliente_id_seq")
@Table(name = "cliente")
public class Cliente extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4699449860340032136L;

	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
	@Column(name = "id_cliente")
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
