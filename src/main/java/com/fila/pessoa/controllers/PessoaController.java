package com.fila.pessoa.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fila.pessoa.dtos.PessoaDto;
import com.fila.pessoa.model.PessoaModel;
import com.fila.pessoa.services.PessoaService;
import com.fila.pessoa.specifications.SpecificationTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;



@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "*",maxAge = 3600)
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<Page<PessoaModel>> getAllPessoas(SpecificationTemplate.PessoaSpec spec,
                                                           @PageableDefault(page = 0, size = 10, sort = "pessoaId", direction = Sort.Direction.ASC) Pageable pageable){
        Page<PessoaModel> pessoaModelPage = pessoaService.findAll(spec, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pessoaModelPage);
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<Object> getOnePessoa(@PathVariable(value = "pessoaId") UUID pessoaId){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(pessoaId);


        if(!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não existe.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(pessoaModelOptional.get());
        }
    }

    @PostMapping("/pessoaRegister")
    public ResponseEntity<Object> registerPessoa(@RequestBody @Validated(PessoaDto.PessoaView.RegistrationPost.class)
                                                @JsonView(PessoaDto.PessoaView.RegistrationPost.class)  PessoaDto pessoaDto){

        if(pessoaService.existsByPessoaName(pessoaDto.getNome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Nome da Pessoa já existe!");
        }

        var pessoaModel = new PessoaModel();
        BeanUtils.copyProperties(pessoaDto, pessoaModel);

        pessoaService.save(pessoaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaModel);
    }


    @PutMapping("/{pessoaId}")
    public ResponseEntity<Object> updatePessoa(@PathVariable(value = "pessoaId") UUID pessoaId,
                                              @RequestBody @Validated(PessoaDto.PessoaView.PessoaPut.class)
                                              @JsonView(PessoaDto.PessoaView.PessoaPut.class)  PessoaDto pessoaDto){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(pessoaId);

        if(!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não existe.");
        } else {
            var pessoaModel = pessoaModelOptional.get();
            pessoaModel.setNome(pessoaDto.getNome());
            pessoaModel.setIdade(pessoaDto.getIdade());
            pessoaModel.setPosicaoNaFila(pessoaDto.getPosicaoNaFila());

            pessoaService.save(pessoaModel);
            return ResponseEntity.status(HttpStatus.OK).body(pessoaModel);
        }
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Object> deletePessoa(@PathVariable(value = "pessoaId") UUID pessoaId){
        Optional<PessoaModel> pessoaModelOptional = pessoaService.findById(pessoaId);


        if(!pessoaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não existe.");
        } else {
            pessoaService.delete(pessoaModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada com sucesso.");
        }
    }

}
