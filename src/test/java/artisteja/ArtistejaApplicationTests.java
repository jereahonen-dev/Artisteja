package artisteja;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import artisteja.web.ArtistejaController;
import artisteja.web.ArtistejaRestController;
import artisteja.web.LabelRestController;

@SpringBootTest
class ArtistejaApplicationTests {

	@Autowired 
	private ArtistejaController artistejaController;

	@Autowired 
	private ArtistejaRestController artistejaRestController;

	@Autowired 
	private LabelRestController labelRestController;

	@Test
	void contextLoads() {
	}

	@Test 
	public void artistControllerLoads() throws Exception {
		assertThat(artistejaController).isNotNull();
	}

	@Test 
	public void artistRestControllerLoads() throws Exception {
		assertThat(artistejaRestController).isNotNull();
	}

	@Test 
	public void labelRestControllerLoads() throws Exception {
		assertThat(labelRestController).isNotNull();
	}
}
