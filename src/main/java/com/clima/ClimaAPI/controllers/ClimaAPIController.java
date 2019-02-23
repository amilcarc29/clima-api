package com.clima.ClimaAPI.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clima.ClimaAPI.PronosticoClima;
import com.clima.ClimaAPI.models.Planeta;
import com.clima.ClimaAPI.repositories.ClimaRepository;
import com.clima.ClimaAPI.repositories.PlanetaRepository;

@RestController
@RequestMapping("/api")
public class ClimaAPIController {

	@Autowired
	private ClimaRepository repository;
	@Autowired
	private PlanetaRepository planetaRepository;

	private static final String SEQUIA = "Sequía";
	private static final String LLUVIOSO = "Lluvioso";
	private static final String CONDICION_OPTIMA = "Condición óptima";

	@GetMapping(value = "/")
	public String home() {
		return "API para generar clima.";
	}

	@GetMapping(value = "/pronostico")
	public String getClimas() {
		JSONObject pronostico = new JSONObject();
		pronostico.put(SEQUIA, repository.findByPronostico(SEQUIA).size());
		pronostico.put(LLUVIOSO, repository.findByPronostico(LLUVIOSO).size());
		pronostico.put(CONDICION_OPTIMA, repository.findByPronostico(CONDICION_OPTIMA).size());
		JSONObject pronosticoAnios = new JSONObject();
		pronosticoAnios.put("10 años", pronostico);
		return pronosticoAnios.toString();
	}

	private JSONObject generarPronosticoExtendido(int aniosEnDias) throws Exception {
		List<JSONObject> pronosticoExtendido = new ArrayList<>();
		List<Planeta> planetas = planetaRepository.findAll();
		JSONObject pronostico = new JSONObject();
		for (int i = 1; i <= aniosEnDias; i++) {
			JSONObject climaObject = new JSONObject(PronosticoClima.getInstancia(repository).pronosticoClima(i, planetas));
			pronosticoExtendido.add(climaObject);
		}
		pronostico.put(SEQUIA, filtrarPronosticos(pronosticoExtendido, SEQUIA).size());
		pronostico.put(LLUVIOSO, filtrarPronosticos(pronosticoExtendido, LLUVIOSO).size());
		pronostico.put(CONDICION_OPTIMA, filtrarPronosticos(pronosticoExtendido, CONDICION_OPTIMA).size());
		JSONObject pronosticoAnios = new JSONObject();
		pronosticoAnios.put(String.valueOf(aniosEnDias) + " años", pronostico);
		return pronosticoAnios;
	}

	private List<JSONObject> filtrarPronosticos(List<JSONObject> pronosticoExtendido, String climaString) {
		return pronosticoExtendido.stream()
				.filter(clima -> climaString.equals(clima.getString("clima")))
				.collect(Collectors.toList());
	}

	@GetMapping(value = "/clima")
	public String getClima(@RequestParam("dias") Integer dias) {
		List<Planeta> planetas = planetaRepository.findAll();
		String pronostico = "";
		try {
			pronostico = PronosticoClima.getInstancia(repository).pronosticoClima(dias, planetas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pronostico;
	}
}