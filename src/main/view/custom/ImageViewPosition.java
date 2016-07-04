/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.view.custom;

import javafx.scene.image.ImageView;

/**
 *
 * @author Antonietta
 */
public class ImageViewPosition {
    
    private ImageView image = new ImageView();
    private int position = 1;
    
    public ImageViewPosition(ImageView image, int position){
        this.image = image;
        this.position = position;
    }
    
    public void setImage(ImageView image){
        this.image = image;
    }
    
     public ImageView getImage(){
        return image;
    }
     
      
    public void setPosition(int position){
        this.position = position;
    }
    
     public int getPosition(){
        return position;
    }
     
     
    
    
}
