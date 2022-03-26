package br.com.ifpb.gerenciadorendereco.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table()
public class Endereco implements Serializable {

    @ManyToOne(targetEntity = Contato.class)
    @JoinColumn(name = "contato_endereco", nullable = false, foreignKey =
    @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "contato_fk"))
    private Contato contato;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_endereco")
    private Long id;

    private String rua;
    private int numero;
    private String complemento;
    private String bairro;
    private int cep;
    private String cidade;
    private String estado;

    public Endereco() {
    }


    public Endereco(Contato contato, Long id, String rua, int numero, String complemento, String bairro, int cep,
                    String cidade, String estado) {
        this.contato = contato;
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }
}
