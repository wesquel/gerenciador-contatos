package br.com.ifpb.gerenciadorendereco.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_contato")
    private Long id;
    private String nome;
    private int RG;
    private int CPF;

    @OneToMany(mappedBy = "contato", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Endereco> enderecos = new ArrayList<Endereco>();

    @ManyToOne(targetEntity = Conta.class)
    @JoinColumn(name = "conta_contato", nullable = false, foreignKey =
    @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "conta_fk"))
    private Conta conta;

    public Contato() {
    }

    public Contato(Long id, String nome, int RG, int CPF, List<Endereco> enderecos, Conta conta) {
        this.id = id;
        this.nome = nome;
        this.RG = RG;
        this.CPF = CPF;
        this.enderecos = enderecos;
        this.conta = conta;
    }



    public void adicionarEndereco(Endereco endereco){
        enderecos.add(endereco);
    }

}
