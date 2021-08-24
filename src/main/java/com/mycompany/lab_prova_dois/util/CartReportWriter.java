/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab_prova_dois.util;

import com.mycompany.lab_prova_dois.repository.model.CartItem;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author kevin
 */
public class CartReportWriter {

    public static int offset = 15;
    private static String[] titles = {"Item", "Descricao", "Quantidade", "Valor Unitário", "Valor Total"};

    public static void writePdf(List<CartItem> cart, float totalValue) {
        try (PDDocument doc = new PDDocument()) {

            PDPage myPage = new PDPage();
            doc.addPage(myPage);

            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {

                cont.beginText();
                cont.setFont(PDType1Font.COURIER, 12);
                cont.setLeading(14.5f);
                String line;
                cont.newLineAtOffset(5, 700);

                line = getLine(titles);
                cont.showText(line);
                cont.newLine();

                Locale locale = new Locale("pt", "BR");
                NumberFormat nf2 = NumberFormat.getInstance(locale);
                Currency currency = Currency.getInstance(locale);
                nf2.setMinimumFractionDigits(currency.getDefaultFractionDigits());

                for (int i = 0; i < cart.size(); i++) {
                    String[] values = getCartValues(i, cart.get(i), nf2);
                    line = getLine(values);
                    cont.showText(line);
                }

                cont.newLine();
                line = "Total";
                cont.showText(StringUtils.leftPad(line, offset * (titles.length - 1), ""));

                line = nf2.format(totalValue);;
                cont.showText(StringUtils.leftPad(line, offset, ""));
                cont.newLine();

                cont.endText();

            } catch (IOException ex) {

            }
            
            File dir = new File("src/main/resources");
            if(!dir.exists())
                dir.mkdir();
            
            doc.save("src/main/resources/cart.pdf");
        } catch (IOException ex) {

        }
    }

    public static void writeTxt(List<CartItem> cart, float totalValue) {
        writePdf(cart, totalValue);
        try {
            PrintWriter writer = new PrintWriter("src/main/resources/cart.txt");
            File myFile = new File("src/main/resources/cart.pdf");

            try (PDDocument doc = PDDocument.load(myFile)) {
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(doc);
                writer.write(text);
                writer.close();
                myFile.delete();
            }

        } catch (IOException ex) {

        }

    }

    private static String getLine(String[] values) {
        String line = "";
        for (String title : values) {
            line += StringUtils.leftPad(title, offset, "");
        }
        return line;
    }

    private static String[] getCartValues(int index, CartItem cartItem, NumberFormat nf2) {
        String desc = cartItem.getDescription();
        int qtd = cartItem.getQuantity();
        String value = nf2.format(cartItem.getUnitaryValue());
        String totalItemValue = nf2.format(cartItem.getTotalValue());

        String[] values = {String.valueOf(index), desc, String.valueOf(qtd), value, totalItemValue};
        return values;
    }

}
