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
@DisplayName("6. Dado que o usuário deseja buscar os 3 personagens mais fortes")
public class PersonagemTop3Test {

    @Nested
    @DisplayName("6.1 Cenários válidos")
    public class CenarioValido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("6.1.1 Quando o usuário buscar os 3 personagens mais fortes deve retornar 3 personagens")
        public void teste1() throws Exception {

            mockMvc.perform(get(ControllerEnumPath.TOP3.path)
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(3)))
                    .andExpect(jsonPath("$[0].codinome").value("Dr. Onipresente Azulado"))
                    .andExpect(jsonPath("$[1].codinome").value("Morcegão"))
                    .andExpect(jsonPath("$[2].codinome").value("Flash Tropeçando"));
        }
    }

    @Nested
    @DisplayName("6.2 Cenários inválidos")
    public class CenarioInvalido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @Sql(scripts = "/data/truncate_table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("6.2.1 Quando o usuário buscar os 3 personagens mais fortes e não houver personagens deve retornar 204")
        public void teste1() throws Exception {

            mockMvc.perform(get(ControllerEnumPath.TOP3.path)
                            .contentType("application/json"))
                    .andExpect(status().isNoContent());
        }
    }
}
