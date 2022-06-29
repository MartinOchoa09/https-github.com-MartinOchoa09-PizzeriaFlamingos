package com.upchiapas.pizza.models;

import com.upchiapas.pizza.models.Pizza;
import java.util.ArrayList;
import java.util.Iterator;

public class CatalogoPizza extends Pizza{
    private ArrayList<Pizza> catalogo;
    public CatalogoPizza(ArrayList<Pizza> catalogo) {
        this.catalogo = catalogo;
    }

    public ArrayList<Pizza> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(ArrayList<Pizza> catalogo) {
        this.catalogo = catalogo;
    }

    @Override
    public String toString() {
        String result = "";
        for (Iterator iterator = catalogo.iterator(); iterator.hasNext();) {
            Pizza pizza = (Pizza) iterator.next();
            result += pizza.getId() + ") " + pizza.getEspecialidad() + ": $" + pizza.getPrecio() + "\n";
        }
        return result;
    }



}
