package s23.Harkkatyo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import s23.Harkkatyo.model.Designer;
import s23.Harkkatyo.model.DesignerRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DesignerRepositoryTests {
	@Autowired
	private DesignerRepository dRepository;
	
	@BeforeEach
	public void setUp() {
		Designer desi1 = new Designer("Aatami");
		Designer desi2 = new Designer("Beetami");
		
		this.dRepository.save(desi1);
		this.dRepository.save(desi2);
	}
	
	@Test
	public void testDesignerExists() {
		assertThat(dRepository.findByDesignerName("Aatami")).hasSize(1);
	}
	
	@Test
	public void testDesignerNotFound() {
		assertThat(dRepository.findByDesignerName("eiole")).hasSize(0);
	}
	
	@Test
	public void testDeletionWorks() {
		Designer desi3 = new Designer("delete");
		dRepository.save(desi3);
		dRepository.delete(desi3);
		assertThat(dRepository.findByDesignerName("delete")).hasSize(0);
	}
}
