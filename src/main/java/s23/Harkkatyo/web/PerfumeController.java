package s23.Harkkatyo.web;

import java.util.HashSet;
import java.util.Optional;

import org.hibernate.mapping.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import s23.Harkkatyo.HarkkatyoApplication;
import s23.Harkkatyo.model.DesignerRepository;
import s23.Harkkatyo.model.Perfume;
import s23.Harkkatyo.model.PerfumeRepository;
import s23.Harkkatyo.model.Perfumer;
import s23.Harkkatyo.model.PerfumerRepository;

@Controller
public class PerfumeController {
	private static final Logger log = LoggerFactory.getLogger(HarkkatyoApplication.class);
	@Autowired
	private PerfumeRepository pRepository;

	@Autowired
	private DesignerRepository dRepository;

	@Autowired
	private PerfumerRepository perRepository;

	@GetMapping("index")
	@ResponseBody
	public String showIndex() {
		return "loaded index page";
	}

	@GetMapping("perfumelist")
	public String perfumelist(Model model) {
		model.addAttribute("perfumes", pRepository.findByOrderByPerfumeNameAsc());
		return "perfumelist";
	}

	// ADDITION
	@GetMapping("addperfume")
	public String addPerfume(Model model) {
		model.addAttribute("perfume", new Perfume());
		model.addAttribute("designers", dRepository.findAll());
		model.addAttribute("perfumers", perRepository.findAll());
		return "addperfume";
	}

	@PostMapping("save")
	public String savePerfume(@Valid @ModelAttribute("perfume") Perfume perfume, BindingResult bindingresult,
			Model model) {
		if (bindingresult.hasErrors()) {
			model.addAttribute("perfume", perfume);
			model.addAttribute("designers", dRepository.findAll());
			model.addAttribute("perfumers", perRepository.findAll());
			return "addperfume";
		}

		pRepository.save(perfume);
		return "redirect:perfumelist";
	}

	// EDIT
	@GetMapping(value = "/edit/{perfumeId}")
	public String editPerfume(@PathVariable("perfumeId") Long perfumeId, Model model) {
		model.addAttribute("perfume", pRepository.findById(perfumeId));

		// get associated perfumers
		model.addAttribute("existingPerfumers", pRepository.findById(perfumeId).orElseThrow().getPerfumers());
		model.addAttribute("designers", dRepository.findAll());
		model.addAttribute("perfumers", perRepository.findAll());
		return "editperfume";
	}

	@PostMapping(value = "/edit/saveEditedPerfume")
	public String saveEditedPerfume(Perfume perfume, Model model) {
		pRepository.save(perfume);
		return "redirect:../perfumelist";
	}

	// APPEND A SINGLE PERFUMER
	@GetMapping(value = "/edit2/{perfumeId}")
	public String editPerfume2(@PathVariable("perfumeId") Long perfumeId, Model model) {
		model.addAttribute("perfume", pRepository.findById(perfumeId));

		// get associated perfumers
		model.addAttribute("existingPerfumers", pRepository.findById(perfumeId).orElseThrow().getPerfumers());
		model.addAttribute("designers", dRepository.findAll());
		model.addAttribute("perfumers", perRepository.findAll());
		return "append";
	}

	@PostMapping(value = "edit2/appendPerfumer")
	public String save2(@ModelAttribute("perfume") Perfume perfume, Model model) {
		Perfume existing = pRepository.findById(perfume.getPerfumeId()).orElseThrow();
		log.info(existing.toString());
		for (Perfumer x : perfume.getPerfumers()) {
			log.info(x.toString());
			existing.addPerfumer(x);
		}
		;

		pRepository.save(existing);
		return "redirect:../perfumelist";
	}

	@GetMapping(value = "/delete/{perfumeId}")
	public String deletePerfume(@PathVariable("perfumeId") Long perfumeId, Model model) {
		pRepository.deleteById(perfumeId);
		return "redirect:../perfumelist";
	}
}
