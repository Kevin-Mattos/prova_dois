/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab_prova_dois.ui.compra;

import com.mycompany.lab_prova_dois.repository.Repository;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author kevin
 */
public class CompraController extends Observable {
    
    Repository repo;
    private CompraState state;
    
    CompraController(Repository repo) {
        this.repo = repo;
        state = new CompraState();
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
