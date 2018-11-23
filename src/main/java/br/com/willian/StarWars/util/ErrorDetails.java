package br.com.willian.StarWars.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails implements Serializable {

    private static final long serialVersionUID = 854590932388853627L;

    private Long timestamp;
    private int status;
    private String titulo;
    private String mensagem;

    @NonNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public ErrorDetails(Long timestamp, int status, String titulo, String mensagem) {
        this.timestamp = timestamp;
        this.status = status;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }
}