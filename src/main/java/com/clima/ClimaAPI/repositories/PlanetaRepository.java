package com.clima.ClimaAPI.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.clima.ClimaAPI.models.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String> {
	Planeta findByNombre(String nombre);
}
