package artisteja;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest 
@AutoConfigureMockMvc(addFilters = false)
public class ArtistejaRestTest {

    @Autowired 
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", authorities = "ADMIN")
    public void getArtistiWorks() throws Exception {

    mockMvc.perform(get("/api/artists"))
           .andExpect(status().isOk())
           .andExpect(content().contentTypeCompatibleWith("application/json"));
}
}
