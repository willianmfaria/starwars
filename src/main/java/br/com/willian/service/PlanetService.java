package br.com.willian.service;

import br.com.willian.exception.ConflictException;
import br.com.willian.exception.NotFoundException;
import br.com.willian.model.Planet;
import br.com.willian.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

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
        if (!planeta.isPresent()) throw new NotFoundException("Planet não encontrado! Id: " + id);
        return planeta.get();
    }

    public Planet salvar(Planet planet) {
        if (planet.getId() != null) {
            Optional<Planet> op = planetRepository.findById(planet.getId());
            if (op.isPresent()) throw new ConflictException("O planet já existe!");
        }
        /*
        try {
            planet.setFilms(films(planet.getName()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        */
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

    /*
    private String films(String planetName) throws URISyntaxException {
        String api = "https://swapi.co/api/planets/?search=";
        JSONObject obj;
        return "0";
    }
    */
}
