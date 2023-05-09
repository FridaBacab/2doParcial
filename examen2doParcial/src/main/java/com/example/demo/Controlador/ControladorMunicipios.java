package com.example.demo.Controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Models.MunicipioDTO;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/Municipios")
public class ControladorMunicipios {

	@GetMapping("{cl}")
	public String startMethod (@PathVariable("cl") String cl, Model model) {
		String titulo = "Pagina API con Spring Boot";
		model.addAttribute("name", cl);
		model.addAttribute("titulo", titulo);
		MunicipioDTO startMunicipioDTO = startMunicipiosDTO (cl);
		model.addAttribute("startMunicipioDTO", startMunicipioDTO);
		return "municipios";
	}
	
	public MunicipioDTO startMunicipiosDTO (String numID) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MunicipioDTO> resp =
				restTemplate
				.getForEntity(
						String.format("https://myappfb-4718b.firebaseio.com/campeche/municipios/%s"+".json", numID),
						MunicipioDTO.class);
		return resp.getBody();
	
	
	}
}
