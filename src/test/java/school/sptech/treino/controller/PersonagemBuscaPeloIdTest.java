package school.sptech.treino.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import school.sptech.treino.util.ControllerEnumPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("2. Dado que o usuário deseja buscar personagem pelo id")
public class PersonagemBuscaPeloIdTest {

    @Nested
    @DisplayName("2.1 Cenários válidos")
    public class CenarioValido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("2.1.1 Quando o usuário buscar por um personagem existente, deve retornar status 200")
        public void teste1() throws Exception {
            int id = 11;
            mockMvc.perform(get(ControllerEnumPath.POR_ID.path, id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(id))
                    .andExpect(jsonPath("$.nome").doesNotExist())
                    .andExpect(jsonPath("$.codinome").value("Arqueiro Verde Nem Tão Letal"))
                    .andExpect(jsonPath("$.poder").value(45.0))
                    .andExpect(jsonPath("$.habilidade").value("Precisão Quase Perfeita"))
                    .andExpect(jsonPath("$.dataNascimento").value("1985-05-16"))
                    .andExpect(jsonPath("$.classificacao").value("Fraco"));
        }
    }

    @Nested
    @DisplayName("2.2 Cenários inválidos")
    public class CenarioInvalido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("2.2.1 Quando o usuário buscar por um personagem inexistente, deve retornar status 404")
        public void teste1() throws Exception {
            int id = 42;
            mockMvc.perform(get(ControllerEnumPath.POR_ID.path, id))
                    .andExpect(status().isNotFound());
        }

        @Test
        @Sql(scripts = "/data/truncate_table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("2.2.2 Quando o usuário buscar por um personagem e não houver personagens cadastrados, deve retornar status 404")
        public void teste2() throws Exception {
            int id = 11;
            mockMvc.perform(get(ControllerEnumPath.POR_ID.path, id))
                    .andExpect(status().isNotFound());
        }
    }
}
