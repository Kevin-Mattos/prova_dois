/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab_prova_dois.util;

import com.mycompany.lab_prova_dois.repository.model.CartItem;
import static com.mycompany.lab_prova_dois.util.CartReportWriter.offset;
import java.text.NumberFormat;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author kevin
 */
public class StringHelper {
    public static String getLine(String[] values) {
        String line = "";
        for (int i = 0; i < values.length; i++) {
            String title = values[i];
            if(i < 2)
                line += StringUtils.rightPad(title, offset, "");
            else
                line += StringUtils.leftPad(title, offset, "");
        }
        return line;
    }

    public static String[] getCartValues(int index, CartItem cartItem, NumberFormat nf2) {
        String desc = cartItem.getDescription();
        int qtd = cartItem.getQuantity();
        String value = nf2.format(cartItem.getUnitaryValue());
        String totalItemValue = nf2.format(cartItem.getTotalValue());

        String[] values = {String.valueOf(index), desc, String.valueOf(qtd), value, totalItemValue};
        return values;
    }
}
