package br.com.cd2.sigabem.services.exceptions;

public class CepNotFoundException extends RuntimeException{
    public static final long serialVersionUID = 1L;

    public CepNotFoundException(Object cep) {
        super("Cep not found " + cep);
    }
}