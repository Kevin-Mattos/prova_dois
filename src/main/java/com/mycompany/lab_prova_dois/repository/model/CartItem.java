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

    @Override
    public String toString() {
        return "CartItem{" + "qtd=" + qtd + ", item=" + item + '}';
    }
}
