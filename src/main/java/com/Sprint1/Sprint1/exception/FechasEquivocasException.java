package com.Sprint1.Sprint1.exception;

public class FechasEquivocasException extends RuntimeException{
    public FechasEquivocasException() {
        super("La fecha de partida debe ser anterior a la fecha de regreso");
    }
}
