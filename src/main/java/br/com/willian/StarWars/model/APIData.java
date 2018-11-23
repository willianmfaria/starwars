package br.com.willian.StarWars.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIData {

    private Integer count;
    private String next;
    private String previous;
    private List<PlanetData> results;
}
