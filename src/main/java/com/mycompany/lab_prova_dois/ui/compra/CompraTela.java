/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab_prova_dois.ui.compra;

import com.mycompany.lab_prova_dois.repository.Repository;
import com.mycompany.lab_prova_dois.repository.model.CartItem;
import com.mycompany.lab_prova_dois.repository.model.Item;
import com.mycompany.lab_prova_dois.ui.compra.CompraState.FinishedExportingFile;
import com.mycompany.lab_prova_dois.ui.compra.CompraState.ShowError;
import com.mycompany.lab_prova_dois.ui.compra.CompraState.ShowItems;
import com.mycompany.lab_prova_dois.ui.compra.CompraState.UpdateCart;
import static com.mycompany.lab_prova_dois.util.CartReportWriter.offset;
import static com.mycompany.lab_prova_dois.util.CartReportWriter.titles;
import static com.mycompany.lab_prova_dois.util.StringHelper.getCartValues;
import static com.mycompany.lab_prova_dois.util.StringHelper.getLine;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author kevin
 */
public class CompraTela extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form CompraTela
     */
    CompraController controller;
    public CompraTela() {
        initComponents();
        controller = new CompraController(Repository.getInstance());
        controller.addObserver(this);
        controller.getItens();
        setView();
    }
    
    private void showItens(List<Item> items) {
        
        ArrayList<String> it = new ArrayList();
        for (Item item : items) {
            it.add(item.toString());
        }
        
        shopList.setListData(it.toArray(String[]::new));
    }    

    private void updateCart(String text) {
        cartText.setText(text);
    }
    
    private void showError(String error) {
        showErrorJOptionPane(error);
    }
    
    private void showFinishedExportingMessage(String message) {
        System.out.println(message);
        showSuccessJOptionPane(message);
    }
    
    private void showErrorJOptionPane(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void showSuccessJOptionPane(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        shopList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        quantityAddField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cartText = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        removeIdField = new javax.swing.JTextField();
        removeButton = new javax.swing.JButton();
        cleanCartButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        exportPdfButton = new javax.swing.JButton();
        exportTxtButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Produtos");

        shopList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(shopList);

        jLabel2.setText("Quantidade");

        addButton.setText("Adicionar Produto");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quantityAddField))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(quantityAddField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Produtos");

        cartText.setColumns(20);
        cartText.setRows(5);
        jScrollPane2.setViewportView(cartText);

        jLabel4.setText("N??mero do item");

        removeButton.setText("Remover");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        cleanCartButton.setText("Limpar Venda");
        cleanCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanCartButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(removeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 281, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cleanCartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(removeIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeButton)
                    .addComponent(cleanCartButton))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        exportPdfButton.setText("Exportar PDF");
        exportPdfButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportPdfButtonActionPerformed(evt);
            }
        });

        exportTxtButton.setText("Exportar em TXT");
        exportTxtButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportTxtButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exportPdfButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(exportTxtButton, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportPdfButton)
                    .addComponent(exportTxtButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        int index = shopList.getSelectedIndex();
        String text = quantityAddField.getText();
        controller.addToCart(index, text);
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        String text = removeIdField.getText();
        controller.removeFromCart(text);
    }//GEN-LAST:event_removeButtonActionPerformed

    private void cleanCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanCartButtonActionPerformed
        controller.cleanCart();
    }//GEN-LAST:event_cleanCartButtonActionPerformed

    private void exportPdfButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportPdfButtonActionPerformed
        controller.exportPdf();
    }//GEN-LAST:event_exportPdfButtonActionPerformed

    private void exportTxtButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportTxtButtonActionPerformed
        controller.exportTxt();
    }//GEN-LAST:event_exportTxtButtonActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CompraTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CompraTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CompraTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CompraTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CompraTela().setVisible(true);
//            }
//        });
//    }

    @Override
    public void update(Observable o, Object o1) {
        CompraState state = ((CompraController) o).getState();
        if (state instanceof ShowItems) {
            showItens(((ShowItems) state).getItems());
        }
        else if (state instanceof UpdateCart) {
            updateCart(((UpdateCart) state).getText());
        }
        else if(state instanceof ShowError) {
            showError(((ShowError) state).getRes().res);
        }
        else if (state instanceof FinishedExportingFile) {
            showFinishedExportingMessage(((FinishedExportingFile) state).getRes().res);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextArea cartText;
    private javax.swing.JButton cleanCartButton;
    private javax.swing.JButton exportPdfButton;
    private javax.swing.JButton exportTxtButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField quantityAddField;
    private javax.swing.JButton removeButton;
    private javax.swing.JTextField removeIdField;
    private javax.swing.JList<String> shopList;
    // End of variables declaration//GEN-END:variables

    private void setView() {
        
//        jTextArea1.disable();
            cartText.setFont(new Font("monospaced", Font.PLAIN, 12));
        
        ((AbstractDocument)quantityAddField.getDocument()).setDocumentFilter(new DocumentFilter(){
        Pattern regEx = Pattern.compile("\\d*");
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
            if(!matcher.matches()){
                return;
            }
            super.replace(fb, offset, length, text, attrs);
            }
    });
        
        ((AbstractDocument)removeIdField.getDocument()).setDocumentFilter(new DocumentFilter(){
        Pattern regEx = Pattern.compile("\\d*");
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
            if(!matcher.matches()){
                return;
            }
            super.replace(fb, offset, length, text, attrs);
            }
    });
        
    }
}
