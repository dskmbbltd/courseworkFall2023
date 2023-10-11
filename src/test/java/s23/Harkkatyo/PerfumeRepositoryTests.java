package s23.Harkkatyo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



import s23.Harkkatyo.model.Perfume;
import s23.Harkkatyo.model.PerfumeRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PerfumeRepositoryTests {
	@Autowired
	private PerfumeRepository pRepository;
	

	@BeforeEach
	public void setUp() {
		Perfume perf1 = new Perfume("a");
		Perfume perf2 = new Perfume("b");

		this.pRepository.save(perf1);
		this.pRepository.save(perf2);

	}
	
	@Test
	public void testPerfumeExists() {
		assertThat(pRepository.findByPerfumeName("a")).hasSize(1);
	}
	
	@Test
	public void testPerfumeCanBeDeleted() {
		Perfume perf3 = new Perfume("delete");
		this.pRepository.save(perf3);
		this.pRepository.deleteById(perf3.getPerfumeId());
		assertThat(pRepository.findByPerfumeName("delete")).hasSize(0);

	}
	
	@Test
	public void testEditPerfumeNameWorks() {
		Perfume editable = pRepository.findOneByPerfumeName("a");
		editable.setPerfumeName("edited");
		assertThat(pRepository.findByPerfumeName("edited")).hasSize(1);
	}
}
