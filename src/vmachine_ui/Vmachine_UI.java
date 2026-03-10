/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vmachine_ui;

import utils.DBConnection;
import view.*;


/**
 *
 * @author lhourde
 */
public class Vmachine_UI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBConnection.initializeDatabase();
        
        Main m = new Main();
        m.show();
    }
    
}
