/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab_prova_dois.ui.compra;

import com.mycompany.lab_prova_dois.repository.model.Item;
import java.util.List;

/**
 *
 * @author kevin
 */
public class CompraState {
    public class ShowItems extends CompraState {
        private List<Item> items;
        ShowItems(List<Item> items){
            this.items = items;
        }
    }
}
