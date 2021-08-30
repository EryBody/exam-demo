/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems.DAO;

/**
 *
 * @author user
 */
public class TestClass {

    public static void main(String[] args) {
        String[] selectedAnswers = {"Precious", "Thompson", "Ekwere","Deborah"};
        String value = "";
        for (int i = 0; i < selectedAnswers.length; i++) {
            value = selectedAnswers[i] + "-yeah\n";
            System.out.print("values: " + value);
        }
       
    }
}
