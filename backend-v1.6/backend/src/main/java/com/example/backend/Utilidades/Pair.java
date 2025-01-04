package com.example.backend.Utilidades;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pair<Obj1, Obj2> {

    @JsonProperty("first") // Para que el campo se serialice con este nombre en JSON
    private Obj1 obj1;

    @JsonProperty("second") // Para que el campo se serialice con este nombre en JSON
    private Obj2 obj2;

    public Pair(Obj1 obj1, Obj2 obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public Pair() {}

    public Obj1 getObj1() {
        return obj1;
    }

    public void setObj1(Obj1 obj1) {
        this.obj1 = obj1;
    }

    public Obj2 getObj2() {
        return obj2;
    }

    public void setObj2(Obj2 obj2) {
        this.obj2 = obj2;
    }
}
