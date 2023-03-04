package com.fila.pessoa.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PessoaDto {
    public interface PessoaView {
        public static interface RegistrationPost {}
        public static interface PessoaPut {}

    }

    private UUID pessoaId;

    @NotBlank(groups = {PessoaView.RegistrationPost.class, PessoaView.PessoaPut.class})
    @Size(min = 4, max = 100, groups = {PessoaView.RegistrationPost.class, PessoaView.PessoaPut.class})
    @JsonView({PessoaView.RegistrationPost.class, PessoaView.PessoaPut.class})
    private String nome;

    @Size(min = 1, max = 150, groups = {PessoaView.RegistrationPost.class, PessoaView.PessoaPut.class})
    @JsonView({PessoaView.RegistrationPost.class, PessoaView.PessoaPut.class})
    private String idade;

    @JsonView({PessoaView.RegistrationPost.class, PessoaView.PessoaPut.class})
    private String posicaoNaFila;
}
