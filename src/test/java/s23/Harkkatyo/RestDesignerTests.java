package s23.Harkkatyo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import s23.Harkkatyo.model.Designer;
import s23.Harkkatyo.model.DesignerRepository;
import s23.Harkkatyo.model.Perfume;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class RestDesignerTests {
	
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webAppContext;
	
	@Autowired
	private DesignerRepository dRepository;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	@Test
	public void statuscheck() throws Exception {
		mockMvc.perform(get("/rest/designers")).andExpect(status().isOk());
	}
	
	@Test
	public void expectingJson() throws Exception {
		mockMvc.perform(get("/rest/designers"))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	public void postWorks() throws Exception {
		Designer testperf = new Designer("testi");
		
		mockMvc.perform(post("/rest/designers/post")
		.content(asJsonString(testperf))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	Designer totest = new Designer("testinimi");
	@Test
	@Transactional
	public void putWorks() throws Exception {
		totest.setDesignerName("changed");
		dRepository.save(totest);
		mockMvc.perform(put("/rest/perfumes/"+totest.getDesignerId().toString())
		.content(asJsonString(totest))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
	        return new ObjectMapper().writeValueAsString(obj);
	    }
}
