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
import static com.mycompany.lab_prova_dois.util.CartReportWriter.offset;
import static com.mycompany.lab_prova_dois.util.CartReportWriter.titles;
import com.mycompany.lab_prova_dois.util.ResEnum;
import static com.mycompany.lab_prova_dois.util.StringHelper.getCartValues;
import static com.mycompany.lab_prova_dois.util.StringHelper.getLine;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import org.apache.commons.lang3.StringUtils;

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
        setState(new UpdateCart(getText()));
    }
    
    public void addToCart(int itemId, String quantityTxt) {
        if(!quantityTxt.isBlank() && !validate(quantityTxt)){
            setState(new ShowError(ResEnum.FAILED_ADD_QUANTITY));
            return;
        }
        int quantity = Integer.valueOf(quantityTxt.isBlank() ? "1" : quantityTxt);
        Item item = getItemForId(itemId);
        if(item == null){
            setState(new ShowError(ResEnum.FAILED_ADD_ID));
            return;
        }
        cart.add(new CartItem(item, quantity));
        setState(new UpdateCart(getText()));
    }
    
    public void removeFromCart(String numTxt) {
        if(!validate(numTxt)){
            setState(new ShowError(ResEnum.FAILED_REMOVE_INVALID_ID));
            return;
        }
        try {
            int num = Integer.valueOf(numTxt);
            cart.remove(num);//todo erro
            setState(new UpdateCart(getText()));
        } catch(IndexOutOfBoundsException e) {
            setState(new ShowError(ResEnum.FAILED_REMOVE_INVALID_ID));
        }        
    }
    
    public void cleanCart() {
        cart.clear();
        setState(new UpdateCart(getText()));
    }
    
    public float getTotalValue() {
        float value = 0f;
        for(CartItem cartItem: cart) {
            value += cartItem.getTotalValue();
        }
        return value;
    }
    
    

    public String getText() {
        String formattedTime = getFormattedTime();
        StringBuilder builder = new StringBuilder();
        builder.append("Mercadinho Ifsc\n");
        builder.append("Data: "+ formattedTime + "\n");
        builder.append("\n");
        
        String line = getLine(titles);
        builder.append(line);
        builder.append("\n");
        

        Locale locale = new Locale("pt", "BR");
        NumberFormat nf2 = NumberFormat.getInstance(locale);
        Currency currency = Currency.getInstance(locale);
        nf2.setMinimumFractionDigits(currency.getDefaultFractionDigits());

        for (int i = 0; i < cart.size(); i++) {
            CartItem cartItem = cart.get(i);
            String[] values = getCartValues(i, cartItem, nf2);
            line = getLine(values);
            builder.append(line);
            builder.append("\n");
        }
   
        line = "Total";
        builder.append(StringUtils.leftPad(line, offset * (titles.length - 1), ""));

        line = nf2.format(getTotalValue());;
        builder.append(StringUtils.leftPad(line, offset, ""));
        
        return builder.toString();
    }
    
    public void exportPdf() {
        String formattedTime = getFormattedTime();
        CartReportWriter.writePdf(cart, getTotalValue(), formattedTime);
        setState(new CompraState.FinishedExportingFile(ResEnum.SUCCESS_CREATED_PDF));
    }
    
    public void exportTxt() {
        CartReportWriter.writeTxt(getText());
        setState(new CompraState.FinishedExportingFile(ResEnum.SUCCESS_CREATED_TXT));

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

    private boolean validate(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    private String getFormattedTime() {
        Date date = Date.from(Instant.now());
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");        
        return dt1.format(date);
    }
}
