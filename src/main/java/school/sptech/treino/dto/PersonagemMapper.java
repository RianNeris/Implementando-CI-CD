package school.sptech.treino.dto;

import school.sptech.treino.entity.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemMapper {

    public static Personagem toEntity(PersonagemCriacaoDto personagemCriacaoDto) {
        Personagem p = new Personagem();
        p.setNome(personagemCriacaoDto.getNome());
        p.setDataNascimento(personagemCriacaoDto.getDataNascimento());
        p.setCodinome(personagemCriacaoDto.getCodinome());
        p.setHabilidade(personagemCriacaoDto.getHabilidade());
        p.setPoder(personagemCriacaoDto.getPoder());
        return p;
    }

    public static PersonagemConsultaDto toDto(Personagem personagem) {
       return new PersonagemConsultaDto(personagem);
    }

    public static List<PersonagemConsultaDto> toDto(List<Personagem> personagens) {

        List<PersonagemConsultaDto> lista = new ArrayList<>();

        for (Personagem p : personagens) {
            lista.add(toDto(p));
        }

        return lista;
    }
}
