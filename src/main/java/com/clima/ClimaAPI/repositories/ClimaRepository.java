package com.clima.ClimaAPI.repositories;

import com.clima.ClimaAPI.models.Clima;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClimaRepository extends MongoRepository<Clima, String> {
  Clima findBy_id(ObjectId _id);
  Clima findByDias(int dias);
  List<Clima> findByPronostico(String pronostico);
}
