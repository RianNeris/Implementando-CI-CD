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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("4. Dado que o usuário deseja buscar personagem pelo codinome")
public class PersonagemBuscaPeloCodinomeTest {

    @Nested
    @DisplayName("4.1 Cenários válidos")
    public class CenarioValido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("4.1.1 Quando o usuário buscar por 'ão', deve retornar 3 personagens")
        public void teste1() throws Exception {

            mockMvc.perform(get(ControllerEnumPath.POR_CODINOME.path)
                            .param("termo", "ão"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$").isNotEmpty())
                    .andExpect(jsonPath("$", hasSize(5)));
        }

        @Test
        @DisplayName("4.1.2 Quando o usuário buscar com ONIpresENTE, deve retornar 1 personagem")
        public void teste2() throws Exception {

            mockMvc.perform(get(ControllerEnumPath.POR_CODINOME.path)
                            .param("termo", "ONIpresENTE"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$").isNotEmpty())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].codinome").value("Dr. Onipresente Azulado"))
                    .andExpect(jsonPath("$[0].poder").value(100.0))
                    .andExpect(jsonPath("$[0].habilidade").value("Manipulação da Realidade"))
                    .andExpect(jsonPath("$[0].dataNascimento").value("1929-08-14"))
                    .andExpect(jsonPath("$[0].classificacao").value("Super Poderoso"));
        }
    }

    @Nested
    @DisplayName("4.2 Cenários inválidos")
    public class CenarioInvalido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("4.2.1 Quando o usuário buscar por 'caio', deve retornar 0 personagens")
        public void teste1() throws Exception {
            mockMvc.perform(get(ControllerEnumPath.POR_CODINOME.path)
                            .param("termo", "caio"))
                    .andExpect(status().isNoContent());
        }

        @Test
        @Sql(scripts = "/data/truncate_table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("4.2.2 Quando o usuário buscar por 'Cap', deve retornar 0 personagens")
        public void teste2() throws Exception {
            mockMvc.perform(get(ControllerEnumPath.POR_CODINOME.path)
                            .param("termo", "Cap"))
                    .andExpect(status().isNoContent());
        }
    }
}
