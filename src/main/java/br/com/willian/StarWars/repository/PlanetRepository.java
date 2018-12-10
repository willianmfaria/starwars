package br.com.willian.StarWars.repository;

import br.com.willian.StarWars.model.Planet;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanetRepository extends MongoRepository<Planet, String> {

    public Planet findByName(String name);
}
