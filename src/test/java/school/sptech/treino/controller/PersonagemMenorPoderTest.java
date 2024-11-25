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
@DisplayName("7. Dado que o usuário deseja buscar o personagem com menor poder")
public class PersonagemMenorPoderTest {

    @Nested
    @DisplayName("7.1 Cenários válidos")
    public class CenarioValido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("7.1.1 Quando o usuário buscar o personagem com menor poder deve retornar o personagem")
        public void teste1() throws Exception {

            mockMvc.perform(get(ControllerEnumPath.MENOR_PODER.path)
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").value(40.0));
        }
    }

    @Nested
    @DisplayName("7.2 Cenários inválidos")
    public class CenarioInvalido {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @Sql(scripts = "/data/truncate_table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
        @DisplayName("7.2.1 Quando o usuário buscar o personagem com menor poder e não houver personagens deve retornar 200 com 0.0 no corpo")
        public void teste1() throws Exception {

            mockMvc.perform(get(ControllerEnumPath.MENOR_PODER.path)
                            .contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").value(0.0));
        }
    }
}
