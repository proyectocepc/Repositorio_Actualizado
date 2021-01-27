/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.JProgressBar;

/**
 *
 * @author Familia Mijares
 */
public class Cargar extends Thread {
    JProgressBar Progreso;
    public Cargar (JProgressBar Progreso){
        super();
        this.Progreso=Progreso;
    }
    @Override
    public void run(){
       for(int a= 1; a<=100;a++){
           Progreso.setValue(a);
           pausa(50);
       } 
       
    }
    public void pausa(int mlseg){
        try{
            Thread.sleep(mlseg);
        }catch(Exception e){
            
        }
    }
}
