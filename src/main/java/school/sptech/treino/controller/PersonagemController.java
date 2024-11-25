package school.sptech.treino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.treino.dto.PersonagemConsultaDto;
import school.sptech.treino.dto.PersonagemCriacaoDto;
import school.sptech.treino.dto.PersonagemMapper;
import school.sptech.treino.entity.Personagem;
import school.sptech.treino.repository.PersonagemRepository;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    @Autowired
    private PersonagemRepository repository;


    private static PersonagemMapper mapper;

    @GetMapping
    public ResponseEntity<List<PersonagemConsultaDto>> listagem() {

        return repository.findAllPersonagemConsultaDto().isEmpty() ?
                status(204).build() :
                status(200).body(repository.findAllPersonagemConsultaDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonagemConsultaDto> buscaPorId(Integer id) {

        if (!repository.existsById(id)){
            status(404).build();
        }
        return status(200).body(new PersonagemConsultaDto(repository.getReferenceById(id)));
    }

    @PostMapping
    public ResponseEntity<PersonagemConsultaDto> cria(PersonagemCriacaoDto personagemCriacaoDto) {

        Personagem p = PersonagemMapper.toEntity(personagemCriacaoDto);
        repository.save(p);
        return status(201).body(new PersonagemConsultaDto(p));
    }

    @GetMapping("/codinome")
    public ResponseEntity<List<PersonagemConsultaDto>> buscarPorCodinomeAproximado(@RequestParam String termo) {
        List<PersonagemConsultaDto> personagens = PersonagemMapper.toDto(repository.findByCodinomeContainingIgnoreCase(termo));
        return personagens.isEmpty() ? status(204).build() : status(200).body(personagens);

    }

    @GetMapping("/nascidos-apos")
    public ResponseEntity<List<PersonagemConsultaDto>> buscarNascidosApos(@RequestParam LocalDate data) {

        List<PersonagemConsultaDto> personagens = PersonagemMapper.toDto(repository.findByDataNascimentoAfter(data));
        return personagens.isEmpty() ? status(204).build() : status(200).body(personagens);

    }

    @GetMapping("/top-3")
    public ResponseEntity<List<PersonagemConsultaDto>> buscarTop3() {
        List<PersonagemConsultaDto> personagens = PersonagemMapper.toDto(repository.findTop3ByOrderByPoderDesc());
        return personagens.isEmpty() ? status(204).build() : status(200).body(personagens);
    }


    @GetMapping("/menor-poder")
    public ResponseEntity<Double> buscarMenorPoder() {

        if(repository.findAll().isEmpty()){
            return status(200).body(0.0);
        }

       return status(200).body(repository.findTop1ByOrderByPoder().getPoder());

    }
}
