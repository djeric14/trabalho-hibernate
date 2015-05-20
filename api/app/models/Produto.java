package models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="produto_id_seq")
@Table(name = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = -1339717517200607306L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id_produto")
    @Basic(optional = false)
    private Integer id;

    @Column(name = "descricao")
    @Basic(optional = false)
    private String nome;

    @Column(name = "preco")
    @Basic(optional = false)
    private Double preco;

    @ManyToOne(fetch=FetchType.LAZY)
    @Basic(optional = false)
    private Fornecedor fornecedor;

    @Column(name = "estoque")
    @Basic(optional = false)
    private Integer estoque;

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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}