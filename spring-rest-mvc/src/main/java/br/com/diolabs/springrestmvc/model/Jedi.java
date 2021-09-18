package br.com.diolabs.springrestmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Entity
@Table(name = "jedi")
public class Jedi {

   
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)   
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio.")
    @Size(min = 3, max = 10, message = "Nome deve conter no mínimo 3 e no máximo 10 caracteres")
    @Column(name="nome")
    private String nome;


    @NotBlank(message = "Sobrenome não pode ser vazio.")    
    @Column(name="sobrenome")
    private String sobrenome;


    public Jedi() {
    }


    public Jedi(Long id, String nome, String sobrenome) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public Jedi(String nome, String sobrenome) {       
        this.nome = nome;
        this.sobrenome = sobrenome;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return this.sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }



   
}
 