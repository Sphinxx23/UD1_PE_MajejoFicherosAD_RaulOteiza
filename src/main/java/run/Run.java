/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package run;

import DAO.IDAO;
import DAO.JugadorDaoLinkedList;
import controlador.Controlador;
import java.util.Scanner;
import vista.IVista;
import vista.Menu;

/**
 *
 * @author RaulOteiza
 */
public class Run {
  
    /**
     * Metodo que se runea en el que se instancian y asocian las clases necesarias 
     */
    public static void main(String[] args) {
               
        Scanner sc = new Scanner(System.in);
        IVista vista = new Menu();
        Controlador controlador = new Controlador(vista);
        IDAO modelo;
        int config;
        String rutFin;

        modelo = new JugadorDaoLinkedList();

        controlador.setModelo(modelo);
        vista.setControlador(controlador);
        
        rutFin=vista.showRut();
        config = vista.showPri();
        vista.show(config, rutFin);
    }
}
