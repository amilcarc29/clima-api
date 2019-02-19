package com.clima.ClimaAPI.controllers;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clima.ClimaAPI.models.Planeta;
import com.clima.ClimaAPI.repositories.PlanetaRepository;

@RestController
@RequestMapping("/api/planetas")
public class PlanetaController {
	
	@Autowired
	private PlanetaRepository repository;
		
	@GetMapping(value = "/")
	public List<Planeta> getClimas() {
	  return repository.findAll();
	}

	@GetMapping(value = "/{nombre}")
	public Planeta getPlanetaByNombre(@PathVariable("nombre") String nombre) {
	  return repository.findByNombre(nombre);
	}

	@PostMapping(value = "/")
	public Planeta createPlaneta(@Valid @RequestBody Planeta planeta) {
	  planeta.set_id(ObjectId.get());
	  repository.save(planeta);
	  return planeta;
	}
}
