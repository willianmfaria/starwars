package br.com.willian.StarWars.util;

import br.com.willian.StarWars.model.APIData;
import br.com.willian.StarWars.model.PlanetData;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Getter
@Setter
@NoArgsConstructor
@Service
public class Request {

    private final String url = "https://swapi.co/api/planets/?format=json&search=";

    public Integer getFilms(String planetName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String urlWithoutSpaces = new String(url + planetName).replace(" ","%20");
        APIData apiData = mapper.readValue(new URL(urlWithoutSpaces), APIData.class);
        PlanetData pl =  new PlanetData();
        for (PlanetData planet : apiData.getResults()) {
            pl = planet;
        }
        return pl.getFilms().size();
    }
}
