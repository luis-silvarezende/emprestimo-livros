package com.br.emakers.emprestimolivros.controllers;

import com.br.emakers.emprestimolivros.dto.PessoaDTO;
import com.br.emakers.emprestimolivros.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoa")
@Tag(name = "Pessoa", description = "Endpoints relacionados à Pessoas")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @Operation(summary = "Lista todas as pessoas que estão cadastradas na API",
            description = "Lista todas as pessoas que estão cadastradas na API",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/all",produces = "application/json")
    public ResponseEntity<List<PessoaDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getAll());
    }

    @Operation(summary = "Lista uma pessoa em especifica pelo id",
            description = "Lista uma pessoa em especifica pelo id",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    public PessoaDTO findById(@PathVariable Long id) {
        return pessoaService.findById(id);
    }

    @Operation(summary = "Faz o registro de uma pessoa nova",
            description = "Faz o registro de uma pessoa, basta informar o nome e cep",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PessoaDTO> createLivro(@RequestBody PessoaDTO pessoaDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.createPessoa(pessoaDTO));
    }

    @Operation(summary = "Atualiza o registro de uma pessoa",
            description = "Atualiza o registro de uma pessoa informando os campos que deseja alterar",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PutMapping(value = "/update/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PessoaDTO> updateLivro(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.updatePessoa(id,pessoaDTO));
    }

    @Operation(summary = "Exclui o registro de uma pessoa",
            description = "Exclui o registro de uma pessoa",
            tags = {"Pessoa"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<String> deleteLivro(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.deletePessoa(id));
    }

}
