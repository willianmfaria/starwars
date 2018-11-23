package br.com.willian.StarWars.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "planets")
public class Planet {

    @Id
    @NotNull
    public String id;
    @NotNull
    public String name;
    @NotNull
    public String climate;
    @NotNull
    public String terrain;
    public Integer films;
}
