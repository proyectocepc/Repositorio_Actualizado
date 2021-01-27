/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.KeyEvent;

/**
 *
 * @author rosan
 */
public  class SoloLetras {
    
    Character s;
       
   public static void letrasYespacio(  KeyEvent evt, String das )
    {
                
        Character s = evt.getKeyChar();
         char b = evt.getKeyChar();
       if(das.length()>=25) evt.consume();
        if((b<'a' || b >'z') && (b<'A' || b >'Z' ) && (s !=KeyEvent.VK_SPACE) && (s != 'ñ') && (s != 'Ñ')) evt.consume();
                      
    }
}
