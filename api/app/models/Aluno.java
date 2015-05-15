package models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

 // Aluno Entity Bean*
 //	@author Carlos Caminha

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="aluno_id_seq")
@Table(name = "aluno")
public class Aluno implements Serializable {

	private static final long serialVersionUID = -2239777236360219431L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    @Basic(optional = false)
    private String nome;

    public Aluno() {
    
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}