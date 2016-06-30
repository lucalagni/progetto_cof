/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.view.custom;

import javafx.scene.control.TextField;
//<?import main.view.custom.*?>

/**
 *
 * @author Antonietta
 */
public class NumberTextField extends TextField{
    
    public NumberTextField(){
        this.setPromptText("Enter only numbers");           
    }
    
//    @Override
//    public void replaceText(int i, int il, String string){
//        if(string.matches("[0-9]") || string.isEmpty()){
//            super.replaceText(il, il, string);
//        }
//    }
//    
//    
//    @Override
//     public void replaceSelection(String string){
//        super.replaceSelection(string);
//    }
    
     @Override
    public void replaceText(int start, int end, String text)
    {
        if (validate(text))
        {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text)
    {
        if (validate(text))
        {
            super.replaceSelection(text);
        }
    }

    private boolean validate(String text)
    {
        return text.matches("[0-9]*");
    }
    
    

}
