package school.sptech.treino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.treino.dto.PersonagemConsultaDto;
import school.sptech.treino.entity.Personagem;

import java.time.LocalDate;
import java.util.List;

public interface PersonagemRepository extends JpaRepository<Personagem, Integer> {


    @Query("select new Personagem(p.id, p.codinome, p.habilidade, p.poder, p.dataNascimento) from Personagem p")
    List<PersonagemConsultaDto> findAllPersonagemConsultaDto();


    List<Personagem> findByCodinomeContainingIgnoreCase(String codinome);


    List<Personagem> findByDataNascimentoAfter(LocalDate data);


    List<Personagem> findTop3ByOrderByPoderDesc();

    Personagem findTop1ByOrderByPoder();




}
