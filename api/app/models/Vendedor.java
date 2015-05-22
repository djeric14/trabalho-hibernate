package models;

import javax.persistence.*;

@Entity
@Table(name = "vendedor")
@PrimaryKeyJoinColumn(name="id_pessoa")
public class Vendedor extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6621094817508679486L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vendedor")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
}
