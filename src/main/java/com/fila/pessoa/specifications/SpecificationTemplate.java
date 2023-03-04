package com.fila.pessoa.specifications;


import com.fila.pessoa.model.PessoaModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.UUID;

public class SpecificationTemplate {

    @And({
            @Spec(path ="nome", spec= Like.class),
            @Spec(path ="idade", spec = Equal.class),
            @Spec(path ="posicaoNaFila", spec = Like.class)

    })
    public interface PessoaSpec extends Specification<PessoaModel> {}
}
