/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab_prova_dois.repository;

/**
 *
 * @author kevin
 */
//EH UM SINGLETON
public class Repository {
    
    private static Repository rep;
    
    public static Repository getInstance() {
        if(rep == null)
            rep = new Repository();
        return rep;
    }
    
}
