package com.example.demo.Controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Models.MDependecias;


@Controller
@RequestMapping("/Dependecia")
public class DependenciasController {
	
	@GetMapping("{sw}")
	public String getDependecy(@PathVariable("sw") String sw, Model model) {
		String titulo = "Pagina API con SprinBot";
		model.addAttribute("name", sw);
		model.addAttribute("titulo", titulo);
		MDependecias starDirectorioDTO = starDirectorioResultDTO (sw);
		model.addAttribute("starDirectorioDTO", starDirectorioDTO);
		return "dependecia";
	}
	
	public MDependecias starDirectorioResultDTO(String sw) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MDependecias> resp =
				restTemplate.getForEntity(
						String.format("https://myappfb-4718b.firebaseio.com/campeche/dependencias/%s"+".json", sw), MDependecias.class);
		return resp.getBody();
	}
	
}
