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
@DisplayName("5. Dado que o usuário deseja buscar personagens nascidos após uma data")
public class PersonagemNascidoAposTest {

    @Nested
    @DisplayName("5.1 Cenários válidos")
    public class CenarioValido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("5.1.1 Quando o usuário buscar por personagens nascidos após 1990-01-01 deve retornar 2 personagens")
        public void teste1() throws Exception {
            mockMvc.perform(get(ControllerEnumPath.POR_DATA_NASCIMENTO.path)
                            .param("data", "1990-01-01")
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].codinome").value("Flash Tropeçando"))
                    .andExpect(jsonPath("$[1].codinome").value("Cyborgue Offline"));
        }
    }

    @Nested
    @DisplayName("5.2 Cenários inválidos")
    public class CenarioInvalido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("5.2.1 Quando o usuário buscar por personagens nascidos após 2020-01-01 deve retornar 0 personagens")
        public void teste1() throws Exception {
            mockMvc.perform(get(ControllerEnumPath.POR_DATA_NASCIMENTO.path)
                            .param("data", "2020-01-01")
                            .contentType("application/json"))
                    .andExpect(status().isNoContent());
        }

        @Test
        @Sql(scripts = "/data/truncate_table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("5.2.2 Quando o usuário buscar por personagens nascidos após 1960-01-01 e não houver personagens, deve retornar status 204")
        public void teste2() throws Exception {
            mockMvc.perform(get(ControllerEnumPath.POR_DATA_NASCIMENTO.path)
                            .param("data", "1960-01-01")
                            .contentType("application/json"))
                    .andExpect(status().isNoContent());
        }
    }
}
