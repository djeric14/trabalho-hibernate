package models;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="fornecedor_id_seq")
@Table(name = "fornecedor")
public class Fornecedor extends BaseModel {

    private static final long serialVersionUID = -603829058490441722L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id_fornecedor")
    @Basic(optional = false)
    private Integer id;

    @Column(name = "nome")
    @Basic(optional = false)
    private String nome;

    @Column(name = "nome_fantasia")
    @Basic(optional = false)
    private String nomeFantasia;

    @Column(name = "cnpj")
    @Basic(optional = false)
    private String cnpj;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_endereco", nullable = false)
    private Endereco endereco;

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

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}