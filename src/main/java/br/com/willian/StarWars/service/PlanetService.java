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

    public List<Planet> listar() {
        List<Planet> planets = new ArrayList<>();
        Iterator<Planet> iterator = planetRepository.findAll().iterator();
        while (iterator.hasNext()) {
            planets.add(iterator.next());
        }
        if (planets.isEmpty()) throw new NotFoundException("Nenhum planeta encontrado!");
        return planets;
    }

    public Planet selecionar(String id) {
        Optional<Planet> planeta = planetRepository.findById(id);
        if (!planeta.isPresent()) throw new NotFoundException("Planeta não encontrado! Id: " + id);
        return planeta.get();
    }

    public Planet salvar(Planet planet) {
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

    public Planet alterar(Planet planet) {
        Planet pla = null;
        if(planet.getId() != null) {
            pla = planetRepository.save(planet);
        }
        return pla;
    }

    public void remover(String id) {
        Optional<Planet> planeta = planetRepository.findById(id);
        if (!planeta.isPresent()) {
            throw new NotFoundException("id: " + id);
        }
        else {
            planetRepository.delete(planeta.get());
        }
    }

    public void remover(Planet planet) {
        planetRepository.delete(planet);
    }
}
