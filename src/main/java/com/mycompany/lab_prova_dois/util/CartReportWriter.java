/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab_prova_dois.util;

import com.mycompany.lab_prova_dois.repository.model.CartItem;
import static com.mycompany.lab_prova_dois.util.StringHelper.getCartValues;
import static com.mycompany.lab_prova_dois.util.StringHelper.getLine;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public static String[] titles = {"Item", "Descrição", "Quantidade", "Valor Unitário", "Valor Total"};

    public static void writePdf(List<CartItem> cart, float totalValue, String formattedTime) {
        try (PDDocument doc = new PDDocument()) {

            PDPage myPage = new PDPage();
            doc.addPage(myPage);

            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {

                cont.beginText();
                cont.setFont(PDType1Font.COURIER, 12);
                cont.setLeading(14.5f);
                String line;
                cont.newLineAtOffset(5, 700);

                line = ("Mercadinho Ifsc");
                cont.showText(line);
                cont.newLine();
                line = ("data: " + formattedTime);
                cont.showText(line); 
                cont.newLine();
                cont.newLine();

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
                    cont.newLine();
                }

                cont.newLine();
                line = "Total";
                cont.showText(StringUtils.leftPad(line, offset * (titles.length - 1), ""));

                line = nf2.format(totalValue);;
                cont.showText(StringUtils.leftPad(line, offset, ""));
                cont.newLine();

                cont.endText();

            } catch (IOException ex) {
//TODO
            }
            
            File dir = new File("src/main/resources");
            if(!dir.exists())
                dir.mkdir();
            
            doc.save("src/main/resources/cart.pdf");
        } catch (IOException ex) {
//TODO
        }
    }

    public static void writeTxt(String text) {

        try (PrintWriter writer = new PrintWriter("src/main/resources/cart.txt")) {
            writer.write(text);
        } catch (FileNotFoundException ex) {
            //TODO
        }

    }
}
