package com.clima.ClimaAPI.repositories;

import com.clima.ClimaAPI.models.ClimaAPI;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClimaAPIRepository extends MongoRepository<ClimaAPI, String> {
  ClimaAPI findBy_id(ObjectId _id);
  ClimaAPI findByDias(int dias);
  List<ClimaAPI> findByPronostico(String pronostico);
}
