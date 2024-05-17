package com.br.emakers.emprestimolivros.exceptions.geral;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Long id){
        super("Entity not found with id" + id);
    }
}
