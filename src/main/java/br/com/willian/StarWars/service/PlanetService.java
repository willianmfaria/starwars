package br.com.willian.StarWars.service;

import br.com.willian.StarWars.exception.ConflictException;
import br.com.willian.StarWars.exception.NotFoundException;
import br.com.willian.StarWars.model.Planet;
import br.com.willian.StarWars.repository.PlanetRepository;
import br.com.willian.StarWars.util.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {

    @Autowired
    private PlanetRepository planetRepository;
    @Autowired
    private Request request;

    public List<Planet> list() {
        List<Planet> planets = new ArrayList<>();
        Iterator<Planet> iterator = planetRepository.findAll().iterator();
        while (iterator.hasNext()) {
            planets.add(iterator.next());
        }
        if (planets.isEmpty()) throw new NotFoundException("Nenhum planeta encontrado!");
        return planets;
    }

    public Planet select(String id) {
        Optional<Planet> planet = planetRepository.findById(id);
        if (!planet.isPresent()) throw new NotFoundException("Planeta não encontrado! Id: " + id);
        return planet.get();
    }

    public Planet selectByName(String name) {
        Planet planet = planetRepository.findByName(name);
        if (planet==null) throw new NotFoundException("Planeta não encontrado! Nome: " + name);
        return planet;
    }

    public Planet save(Planet planet) {
        if (planet.getId() != null) {
            Optional<Planet> op = planetRepository.findById(planet.getId());
            if (op.isPresent()) throw new ConflictException("O planeta já existe!");
        }
        try {
            planet.setFilms(request.getFilms(planet.getName()));
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return planetRepository.save(planet);
    }

    public Planet update(Planet planet) {
        Planet pla = null;
        if(planet.getId() != null) {
            pla = planetRepository.save(planet);
        }
        return pla;
    }

    public void delete(String id) {
        Optional<Planet> planet = planetRepository.findById(id);
        if (!planet.isPresent()) {
            throw new NotFoundException("Planeta não encontrado! Id: " + id);
        }
        else {
            planetRepository.delete(planet.get());
        }
    }

    public void delete(Planet planet) {
        planetRepository.delete(planet);
    }
}
