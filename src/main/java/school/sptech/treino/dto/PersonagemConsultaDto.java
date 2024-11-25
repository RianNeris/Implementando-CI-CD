package school.sptech.treino.dto;

import org.springframework.cglib.core.Local;
import school.sptech.treino.entity.Personagem;

import java.time.LocalDate;
import java.time.Period;

public class PersonagemConsultaDto {

    private Integer id;

    private LocalDate dataNascimento;

    private String codinome;

    private String habilidade;

    private Double poder;

    private String classificacao;

    private Integer idadeAproximada;



    public PersonagemConsultaDto(Personagem p) {
        this.id = p.getId();
        this.dataNascimento = p.getDataNascimento();
        this.codinome = p.getCodinome();
        this.habilidade = p.getHabilidade();
        this.poder = p.getPoder();
        this.classificacao = definirClassificacao(p.getPoder());
        this.idadeAproximada = definirIdade(p.getDataNascimento());
    }

    public String definirClassificacao(Double poder) {

        if (poder <= 20) {
            return "Muito fraco";

        } else if (poder > 20 && poder <= 40) {
            return "Fraco";

        } else if (poder > 40 && poder <= 70) {
            return "Mediano";

        } else if (poder > 70 && poder <= 80) {
            return "Forte";

        } else if (poder > 80) {
            return "Muito Forte";
        }
        return null;
    }

    public Integer definirIdade(LocalDate dtNac){

        LocalDate dtAtual = LocalDate.now();
        Period periodo = Period.between(dtNac, dtAtual);
        return periodo.getYears();

    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCodinome() {
        return codinome;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public Double getPoder() {
        return poder;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public Integer getIdadeAproximada() {
        return idadeAproximada;
    }
}
