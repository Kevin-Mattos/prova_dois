/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab_prova_dois.repository;

import com.mycompany.lab_prova_dois.repository.model.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
//EH UM SINGLETON
public class Repository {
    
    private static Repository rep;
    private List<Item> items = new ArrayList();
    
    public static Repository getInstance() {
        if(rep == null) {
            rep = createRepository();
        }
        return rep;
    }
    
    private static Repository createRepository() {
        Repository rep = new Repository();
        rep.items.add(new Item(0, "Refrigerante 2L", 6f));        
        rep.items.add(new Item(1, "Biscoito Pacote", 3f));        
        rep.items.add(new Item(2, "Pão kg", 10f));        
        rep.items.add(new Item(3, "Desodorante", 12f));        
        rep.items.add(new Item(4, "Amaciante 3L", 20f));
        rep.items.add(new Item(5, "Sabão em pó", 15f));
        rep.items.add(new Item(6, "Arroz kg", 7f));
        rep.items.add(new Item(7, "Feijão kg", 8f));
                
        return rep;
    }

    public List<Item> getItems() {
        return items;
    }    
}
