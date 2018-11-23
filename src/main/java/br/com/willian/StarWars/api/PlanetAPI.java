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

    @GetMapping("/listarPlanetas")
    public ResponseEntity<List<Planet>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(planetService.listar());
    }

    @GetMapping("/selecionarPlaneta/{planetId}")
    public ResponseEntity<Planet> selecionar(@PathVariable("planetId") String planetId) {
        return ResponseEntity.status(HttpStatus.OK).body(planetService.selecionar(planetId));
    }

    @PostMapping("/adicionarPlaneta")
    public ResponseEntity<Void> adicionar(@RequestBody @Valid Planet planet) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planetService.salvar(planet).getId()).toUri()).build();
    }

    @PutMapping("/alterarPlaneta")
    public ResponseEntity<Planet> alterar(@RequestBody @Valid Planet planet) {
        return ResponseEntity.status(HttpStatus.OK).body(planetService.alterar(planet));
    }
    @DeleteMapping("/removerPlaneta/{planetId}")
    public ResponseEntity<Void> remover(@PathVariable("planetId") String planetId) {
        planetService.remover(planetId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/removerPlaneta")
    public ResponseEntity<Void> remover(@RequestBody @Valid Planet planet) {
        planetService.remover(planet);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
