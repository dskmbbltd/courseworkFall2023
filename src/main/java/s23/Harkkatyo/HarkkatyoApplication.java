package s23.Harkkatyo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

import s23.Harkkatyo.model.AppUser;
import s23.Harkkatyo.model.AppUserRepository;
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
	public CommandLineRunner perfumeTestData(PerfumeRepository pRepository, DesignerRepository dRepository, PerfumerRepository perRepository, AppUserRepository userRepository) {
		return (args) -> {
			log.info("adding new designers");
			dRepository.save(new Designer ("Prada"));
			dRepository.save(new Designer ("Acqua di Parma"));
			
			log.info("adding new perfumers");
			Perfumer perf1 = perRepository.save(new Perfumer ("Daniela Andrier"));
			
			Perfumer perf2 = perRepository.save(new Perfumer ("François Demachy"));
			
			Perfumer Beta = perRepository.save(new Perfumer ("Beta"));
			
			
			AppUser user1 = new AppUser("user","$2a$10$OFBvfxQTiXtNR9RUT/6PXO7m6iuHAT9S134eHFTYB5xcMLYPAUyFO", "USER");
			AppUser user2 = new AppUser("admin","$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
			
			log.info("adding new perfumes");
			pRepository.save(new Perfume("Luna Rossa", dRepository.findByDesignerName("Prada").get(0)));
			pRepository.save(new Perfume("Ambra", dRepository.findByDesignerName("Acqua di Parma").get(0)));
			
			log.info("fetch all perfumes");
			for (Perfume perfume : pRepository.findAll()) {
				log.info(perfume.toString());
			}

		};
	}
}
