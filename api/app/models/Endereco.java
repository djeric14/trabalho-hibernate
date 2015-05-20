package models;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="endereco_id_seq")
@Table(name = "endereco")
public class Endereco extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8329398961902370895L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id_endereco")
    private Integer id;
	
	@Column(name = "latitude")
    @Basic(optional = false)
    private Integer latitude;
    
    @Column(name = "longitude")
    @Basic(optional = false)
    private Integer longitude;
    
    @Column(name = "logradouro")
    @Basic(optional = false)
    private String logradouro;
    
    @Column(name = "numero")
    @Basic(optional = false)
    private Integer numero;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", fetch=FetchType.LAZY)
    private Collection<Pessoa> pessoas;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    public Collection<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(Collection<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Endereco() {
    
    }

	public Integer getLatitude() {
		return latitude;
	}



	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}



	public Integer getLongitude() {
		return longitude;
	}



	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}



	public String getLogradouro() {
		return logradouro;
	}



	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}



	public Integer getNumero() {
		return numero;
	}



	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	
}