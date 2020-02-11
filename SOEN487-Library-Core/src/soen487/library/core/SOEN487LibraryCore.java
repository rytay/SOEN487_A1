/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soen487.library.core;

/**
 *
 * @author xavie
 */
public class SOEN487LibraryCore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Book test = new Book();
        Book test2 = new Book();
        
        System.out.println(test.hashCode());
        System.out.println(test2.hashCode());

    }
    
}
