package school.sptech.treino.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import school.sptech.treino.util.ControllerEnumPath;
import school.sptech.treino.util.ErrorMessageValidator;
import school.sptech.treino.util.JsonFormatter;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("3. Dado que o usuário deseja criar um personagem")
public class PersonagemCriacaoTest {

    @Nested
    @DisplayName("3.1 Cenários válidos")
    public class CenariosValidos {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("3.1.1 Quando o usuário criar um personagem com dados válidos, deve retornar status 201")
        public void teste1() throws Exception {

            Map<String, Object> personagem = Map.of(
                    "nome", "Mark Grayson",
                    "codinome", "Invencível",
                    "poder", 100.0,
                    "habilidade", "Voo, Super Força, Regeneração",
                    "dataNascimento", "2003-01-22"
            );

            String json = JsonFormatter.format(personagem);

            mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnumPath.URL_BASE.path)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.nome").doesNotExist())
                    .andExpect(jsonPath("$.codinome").value("Invencível"))
                    .andExpect(jsonPath("$.poder").value(100.0))
                    .andExpect(jsonPath("$.habilidade").value("Voo, Super Força, Regeneração"))
                    .andExpect(jsonPath("$.dataNascimento").value("2003-01-22"))
                    .andExpect(jsonPath("$.classificacao").value("Super Poderoso"));
        }
    }

    @Nested
    @DisplayName("3.2 Cenários inválidos")
    public class CenariosInvalidos {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("3.2.1 Quando o usuário criar um personagem com dados inválidos, deve retornar status 400")
        public void teste1() throws Exception {

            List<String> errors = List.of(
                    "must be greater than or equal to 10.0",
                    "size must be between 5 and 30",
                    "must not be blank",
                    "must not be blank",
                    "must be a date in the past or in the present"
            );

            Map<String, Object> personagem = Map.of(
                    "nome", "",
                    "poder", -5.0,
                    "habilidade", "Invincible voa e tem força sobre-humana.",
                    "dataNascimento", "2030-01-22"
            );

            String json = JsonFormatter.format(personagem);

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(ControllerEnumPath.URL_BASE.path)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            ErrorMessageValidator.assertContainsErrorMessages(mvcResult, errors);
        }
    }
}
