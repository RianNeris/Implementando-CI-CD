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
@DisplayName("1. Dado que o usuário deseja listar personagens")
class PersonagemListagemTest {

    @Nested
    @DisplayName("1.1 Cenários válidos")
    public class CenariosValidos {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("1.1.1 Quando o usuário listar personagens e houver personagens cadastrados, deve retornar status 200")
        public void teste1() throws Exception {
            mockMvc.perform(get(ControllerEnumPath.URL_BASE.path))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(16)));
        }
    }

    @Nested
    @DisplayName("1.2 Cenários inválidos")
    public class CenariosInvalidos {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @Sql(scripts = "/data/truncate_table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("1.2.1 Quando o usuário listar personagens e não houver personagens cadastrados, deve retornar status 204")
        public void teste1() throws Exception {
            mockMvc.perform(get(ControllerEnumPath.URL_BASE.path))
                    .andExpect(status().isNoContent());
        }
    }
}