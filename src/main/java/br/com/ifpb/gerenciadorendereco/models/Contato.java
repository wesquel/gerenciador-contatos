package br.com.ifpb.gerenciadorendereco.models;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_contato")
    private Long id;

    @NotBlank(message = "O Nome não pode está vazio!")
    private String nome;

    @Digits(integer = 7, fraction = 0, message = "O RG Deve ser constituido somente por digitos")
    @Positive(message = "O numero do RG deve ser maior que zero!")
    @Min(value = 1000000, message = "O numero do RG deve possuir 7 digitos")
    @Max(value = 9999999,message = "O numero do RG deve possuir 7 digitos")
    private int RG;

    @Digits(integer = 11, fraction = 0, message = "O CPF Deve ser constituido somente por digitos")
    @Positive(message = "O numero do CPF deve ser maior que zero!")
    private long CPF;

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
