package com.clima.ClimaAPI.repositories;

import com.clima.ClimaAPI.models.ClimaAPI;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClimaAPIRepository extends MongoRepository<ClimaAPI, String> {
  ClimaAPI findBy_id(ObjectId _id);
  ClimaAPI findByDias(int dias);
}
