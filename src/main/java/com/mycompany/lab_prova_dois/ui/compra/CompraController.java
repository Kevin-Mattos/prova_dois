/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab_prova_dois.ui.compra;

import com.mycompany.lab_prova_dois.repository.Repository;
import com.mycompany.lab_prova_dois.repository.model.CartItem;
import com.mycompany.lab_prova_dois.repository.model.Item;
import com.mycompany.lab_prova_dois.ui.compra.CompraState.ShowError;
import com.mycompany.lab_prova_dois.ui.compra.CompraState.ShowItems;
import com.mycompany.lab_prova_dois.ui.compra.CompraState.UpdateCart;
import com.mycompany.lab_prova_dois.util.CartReportWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author kevin
 */
public class CompraController extends Observable {
    
    private Repository repo;
    private CompraState state;
    private List<Item> items;
    private List<CartItem> cart;
    
    CompraController(Repository repo) {
        this.repo = repo;
        state = new CompraState();   
        cart = new ArrayList();
    }
    
    public void getItens() {
        items = repo.getItems();
        setState(new ShowItems(items));
    }
    
    public void addToCart(int itemId, int quantity) {
        Item item = getItemForId(itemId);
        if(item == null){
            setState(new ShowError("Selecione_id_valido"));
            return;
        }
        cart.add(new CartItem(item, quantity));
        setState(new UpdateCart(cart));
    }
    
    public void removeFromCart(int num) {
        try {
            cart.remove(num);//todo erro
            setState(new UpdateCart(cart));
        } catch(IndexOutOfBoundsException e) {
            setState(new ShowError("Remova_Valido_id"));
        }        
    }
    
    public void cleanCart() {
        cart.clear();
        setState(new UpdateCart(cart));
    }
    
    public float getTotalValue() {
        float value = 0f;
        for(CartItem cartItem: cart) {
            value += cartItem.getTotalValue();
        }
        return value;
    }
    
    public void exportPdf() {
        CartReportWriter.writePdf(cart, getTotalValue());
        setState(new CompraState.FinishedExportingFile("SUCCESS_RESOURCE"));
    }
    
    public void exportTxt() {
        CartReportWriter.writeTxt(cart, getTotalValue());
        setState(new CompraState.FinishedExportingFile("SUCCESS_RESOURCE"));

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

    private Item getItemForId(int id) {
        for(Item item: items) {
            if(item.getId() == id)
                return item;
        }
        return null;
    }
}
