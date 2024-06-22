package org.controller.Pet;

public class MapDataObject {
    private final String titulo;
    private final String descricao;

    public MapDataObject(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
