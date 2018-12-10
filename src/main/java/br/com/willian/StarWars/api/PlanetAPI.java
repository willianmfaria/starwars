package br.com.willian.StarWars.api;

import br.com.willian.StarWars.model.Planet;
import br.com.willian.StarWars.service.PlanetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlanetAPI {

    @Autowired
    private PlanetService planetService;

    @GetMapping("/planetas")
    public ResponseEntity<List<Planet>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(planetService.list());
    }

    @GetMapping("/planetas/{planetId}")
    public ResponseEntity<Planet> select(@PathVariable("planetId") String planetId) {
        return ResponseEntity.status(HttpStatus.OK).body(planetService.select(planetId));
    }

    @GetMapping("/planetas/nome/{planetName}")
    public ResponseEntity<Planet> selectByName(@PathVariable("planetName") String planetName) {
        return ResponseEntity.status(HttpStatus.OK).body(planetService.selectByName(planetName));
    }

    @PostMapping("/planetas")
    public ResponseEntity<Void> save(@RequestBody @Valid Planet planet) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planetService.save(planet).getId()).toUri()).build();
    }

    @PutMapping("/planetas")
    public ResponseEntity<Planet> update(@RequestBody @Valid Planet planet) {
        return ResponseEntity.status(HttpStatus.OK).body(planetService.update(planet));
    }
    @DeleteMapping("/planetas/{planetId}")
    public ResponseEntity<Void> delete(@PathVariable("planetId") String planetId) {
        planetService.delete(planetId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/planetas")
    public ResponseEntity<Void> delete(@RequestBody @Valid Planet planet) {
        planetService.delete(planet);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
