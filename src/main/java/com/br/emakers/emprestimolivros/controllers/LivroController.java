package com.br.emakers.emprestimolivros.controllers;

import com.br.emakers.emprestimolivros.dto.EmprestimoRequestDTO;
import com.br.emakers.emprestimolivros.dto.LivroDTO;
import com.br.emakers.emprestimolivros.dto.PessoaDTO;
import com.br.emakers.emprestimolivros.services.LivroService;
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
@RequestMapping(value = "/livro")
@Tag(name = "Livro", description = "Endpoints relacionados à livros")
public class LivroController {

    @Autowired
    LivroService livroService;

    @Operation(summary = "Lista todos os livros cadastrados na API",
            description = "Lista todos os livros cadastrados na API",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<LivroDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.getAll());
    }

    @Operation(summary = "Lista um livro em especifico baseado no id",
            description = "Lista um livro em especifico baseado no id",
            tags = {"Livro"},
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
    public LivroDTO findById(@PathVariable Long id){
        return livroService.findById(id);
    }

    @Operation(summary = "Regista um novo Livro na API",
            description = "Regista um novo Livro na API digitando os campos, titulo, autor e data de lançamento",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public ResponseEntity<LivroDTO> createLivro(@RequestBody LivroDTO livroDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.createLivro(livroDTO));
    }

    @Operation(summary = "Atualiza um registro do Livro na API",
            description = "Atualiza um registro do Livro na API baseado no campo que deseja alterar",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<LivroDTO> updateLivro(@PathVariable Long id, @RequestBody LivroDTO livroDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.updateLivro(id,livroDTO));
    }

    @Operation(summary = "Exclui o registro de um livro na API",
            description = "Exclui o registro de um livro na API baseado no id do livro",
            tags = {"Livro"},
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
        return ResponseEntity.status(HttpStatus.OK).body(livroService.deleteLivro(id));
    }

    @Operation(summary = "Realiza o emprestimo de um livro registrado na API",
            description = "Realiza o emprestimo de um livro registrado na API baseado no seu id",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PatchMapping(value = "/emprestimo", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> emprestimo(@RequestBody EmprestimoRequestDTO emprestimoRequestDTO) {
        livroService.emprestimo(emprestimoRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Devolve um livro que foi emprestado para uma pessoa",
            description = "Devolve um livro que foi emprestado para uma pessoa digitando o id do livro e da pessoa",
            tags = {"Livro"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PatchMapping(value = "/devolver", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> devolver(@RequestBody EmprestimoRequestDTO emprestimoRequestDTO) {
        livroService.devolver(emprestimoRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
