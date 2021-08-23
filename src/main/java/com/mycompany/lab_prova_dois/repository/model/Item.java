/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab_prova_dois.repository.model;

/**
 *
 * @author kevin
 */
public class Item {
    private int id;
    private String description;
    private float unitaryValue;

    public Item(int id, String description, float unitaryValue) {
        this.id = id;
        this.description = description;
        this.unitaryValue = unitaryValue;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getUnitaryValue() {
        return unitaryValue;
    }

    public void setUnitaryValue(float unitaryValue) {
        this.unitaryValue = unitaryValue;
    }

    @Override
    public String toString() {
        return "\nItem{" + "id=" + id + ", description=" + description + ", unitaryValue=" + unitaryValue + "}";
    }    
}
