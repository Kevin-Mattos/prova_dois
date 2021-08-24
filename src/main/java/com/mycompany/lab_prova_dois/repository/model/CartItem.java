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
public class CartItem {
    
    private int qtd;
    private Item item;

    public CartItem(Item item, int qtd) {
        this.qtd = qtd;
        this.item = item;
    }
    
    public float getTotalValue() {
        return qtd * item.getUnitaryValue();
    }
    
    public String getDescription() {
        return item.getDescription(); 
    }
    
    public float getUnitaryValue() {
        return item.getUnitaryValue(); 
    }
    
    public int getQuantity() {
        return qtd;
    }

    @Override
    public String toString() {
        return item.getDescription() + "    " + qtd + "    " + item.getUnitaryValue() + "    " + getTotalValue();
    }
}
