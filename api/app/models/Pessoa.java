package models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy=InheritanceType.JOINED)
public class Pessoa extends BaseModel {

	private static final long serialVersionUID = 9052132527752407992L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Integer id;
	
	@Column(name = "nome")
    @Basic(optional = false)
    private String nome;

	@Column(name = "cpf")
    @Basic(optional = false)
    private String cpf;

	@Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    @Basic(optional = false)
    private Date dataNascimento;
	
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_endereco", nullable = false)
	private Endereco endereco;
	
	public Pessoa() {
		super();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	
	
}
