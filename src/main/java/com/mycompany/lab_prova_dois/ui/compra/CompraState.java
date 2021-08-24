/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab_prova_dois.ui.compra;

import com.mycompany.lab_prova_dois.repository.model.CartItem;
import com.mycompany.lab_prova_dois.repository.model.Item;
import com.mycompany.lab_prova_dois.util.ResEnum;
import java.util.List;

/**
 *
 * @author kevin
 */
public class CompraState {

    static public class ShowItems extends CompraState {

        private List<Item> items;

        ShowItems(List<Item> items) {
            this.items = items;
        }

        public List<Item> getItems() {
            return items;
        }
    }

    static public class UpdateCart extends CompraState {

        private String text;

        UpdateCart(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    static public class ShowError extends CompraState {

        private ResEnum res;

        ShowError(ResEnum res) {
            this.res = res;
        }

        public ResEnum getRes() {
            return res;
        }
    }
        static public class FinishedExportingFile extends CompraState {

        private ResEnum res;

        FinishedExportingFile(ResEnum res) {
            this.res = res;
        }

        public ResEnum getRes() {
            return res;
        }
    }
}
