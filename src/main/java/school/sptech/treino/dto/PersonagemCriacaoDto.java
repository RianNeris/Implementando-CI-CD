package school.sptech.treino.dto;

import school.sptech.treino.entity.Personagem;

import java.time.LocalDate;

public class PersonagemCriacaoDto {


    private LocalDate dataNascimento;

    private String nome;

    private String codinome;

    private String habilidade;

    private Double poder;

    public PersonagemCriacaoDto() {
    }

    public PersonagemCriacaoDto(Personagem p) {
        this.dataNascimento = p.getDataNascimento();
        this.nome = p.getNome();
        this.codinome = p.getCodinome();
        this.habilidade = p.getHabilidade();
        this.poder = p.getPoder() != null ? p.getPoder() : 0.0;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodinome() {
        return codinome;
    }

    public void setCodinome(String codinome) {
        this.codinome = codinome;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public Double getPoder() {
        return poder;
    }

    public void setPoder(Double poder) {
        this.poder = poder;
    }
}
