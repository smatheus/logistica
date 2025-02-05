package br.luizalabs.desafio.logistica.exception;

public class FileIsEmptyException extends RuntimeException {

    public FileIsEmptyException(String message){
        super(message);
    }

}
