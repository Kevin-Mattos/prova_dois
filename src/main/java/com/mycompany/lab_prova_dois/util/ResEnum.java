/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab_prova_dois.util;

/**
 *
 * @author kevin
 */
public enum ResEnum {
    FAILED_REMOVE_INVALID_ID(1, "FAILED_REMOVE", "Falha ao remover, escolha um id válido"),
    FAILED_ADD_QUANTITY(2, "FAILED_ADD", "Falha ao adicionar, escolha uma quantidade válida"),
    FAILED_ADD_ID(3, "FAILED_ADD_ID", "Falha ao adicionar, escolha um id válido"),
    SUCCESS_CREATED_PDF(4, "SUCCESS_CREATED_PDF", "PDF criado com sucesso"),
    SUCCESS_CREATED_TXT(5, "SUCCESS_CREATED_TXT", "TXT Criado com sucesso");
    
    public final int id;
    public final String strId;
    public final String res;

    private ResEnum(int id, String strId, String res) {
        this.id = id;
        this.strId = strId;
        this.res = res;
    }
    
}
