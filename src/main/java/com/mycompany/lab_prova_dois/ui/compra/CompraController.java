/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab_prova_dois.ui.compra;

import com.mycompany.lab_prova_dois.repository.Repository;
import com.mycompany.lab_prova_dois.repository.model.Item;
import com.mycompany.lab_prova_dois.ui.compra.CompraState.ShowItems;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author kevin
 */
public class CompraController extends Observable {
    
    Repository repo;
    private CompraState state;
    private List<Item> items;
    private List<Item> cart;
    
    CompraController(Repository repo) {
        this.repo = repo;
        state = new CompraState();   
        cart = new ArrayList();
    }
    
    public void getItens() {
        items = repo.getItems();
        setState(new ShowItems(items));
    }
    
    public void addToCart(Item item) {
        cart.add(item);
    }
    
    public CompraState getState() {
        return state;
    }
    
    private void setState(CompraState state) {
        this.state = state;
        notifyListeners();
    }
    
    private void notifyListeners() {
        setChanged();
	notifyObservers();
    }     
}
