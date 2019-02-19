package com.clima.ClimaAPI.controllers;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clima.ClimaAPI.PronosticoClima;
import com.clima.ClimaAPI.models.ClimaAPI;
import com.clima.ClimaAPI.models.Planeta;
import com.clima.ClimaAPI.repositories.ClimaAPIRepository;
import com.clima.ClimaAPI.repositories.PlanetaRepository;

@RestController
@RequestMapping("/api")
public class ClimaAPIController {

	@Autowired
	private ClimaAPIRepository repository;
	@Autowired
	private PlanetaRepository planetaRepository;

	@GetMapping(value = "/")
	public List<ClimaAPI> getClimas() {
	  return repository.findAll();
	}

	@PostMapping(value = "/")
	public ClimaAPI createClima(@Valid @RequestBody ClimaAPI clima) {
	  clima.set_id(ObjectId.get());
	  repository.save(clima);
	  return clima;
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