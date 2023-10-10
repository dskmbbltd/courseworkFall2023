package s23.Harkkatyo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import s23.Harkkatyo.model.Perfumer;
import s23.Harkkatyo.model.PerfumerRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PerfumerRepositoryTests {
	@Autowired
	private PerfumerRepository perRepository;
	
	@BeforeEach
	public void setUp() {
		Perfumer perfr1 = new Perfumer("aatami");
		Perfumer perfr2 = new Perfumer("beetami");
		this.perRepository.save(perfr1);
		this.perRepository.save(perfr2);
	}
	
	@Test
	public void testPerfumerExists() {
		assertThat(perRepository.findOneByPerfumerName("aatami"));
	}
	
	@Test
	public void testPerfumerCanBeDeleted() {
		Perfumer perfr3 = new Perfumer("delete");
		this.perRepository.save(perfr3);
		this.perRepository.delete(perfr3);
		assertNull(perRepository.findOneByPerfumerName("delete"));
	}
	
	@Test
	public void testEditPerfumerWorks() {
		Perfumer editableprfr = perRepository.findOneByPerfumerName("aatami");
		editableprfr.setPerfumerName("ootomi");
		assertNotNull(perRepository.findOneByPerfumerName("ootomi"));
	}
}
