package s23.Harkkatyo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.ArrayList;


import s23.Harkkatyo.model.Designer;
import s23.Harkkatyo.model.DesignerRepository;
import s23.Harkkatyo.model.Perfume;
import s23.Harkkatyo.model.PerfumeRepository;
import s23.Harkkatyo.model.Perfumer;
import s23.Harkkatyo.model.PerfumerRepository;



@SpringBootApplication
public class HarkkatyoApplication {
	private static final Logger log = LoggerFactory.getLogger(HarkkatyoApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(HarkkatyoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner perfumeTestData(PerfumeRepository pRepository, DesignerRepository dRepository, PerfumerRepository perRepository) {
		return (args) -> {
			log.info("adding new designers");
			dRepository.save(new Designer ("Prada"));
			dRepository.save(new Designer ("Acqua di Parma"));
			
			log.info("adding new perfumers");
			Perfumer test = perRepository.save(new Perfumer ("Test"));
			
			Perfumer Alpha = perRepository.save(new Perfumer ("A"));
			
			Perfumer Beta = perRepository.save(new Perfumer ("B"));
			
			LinkedHashSet<Perfumer> a = new LinkedHashSet<>();
			a.add(Alpha);
			a.add(Beta);
			
			LinkedHashSet<Perfumer> b = new LinkedHashSet<>();
			b.add(test);
			
			log.info("adding new perfumes");
			pRepository.save(new Perfume("Luna Rossa", dRepository.findByDesignerName("Prada").get(0), a));
			pRepository.save(new Perfume("Ambra", dRepository.findByDesignerName("Acqua di Parma").get(0), b));
			
			log.info("fetch all perfumes");
			for (Perfume perfume : pRepository.findAll()) {
				log.info(perfume.toString());
			}

		};
	}
}
