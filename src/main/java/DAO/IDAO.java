/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;


public interface IDAO<T> {

    public abstract String insertar(T o);
    public abstract String modificar (T nuevo);
    public abstract String borrar (T viejo);
    public abstract String consultarPorId (T o); 
    public abstract String listar ();
    public void reinicio();
    public void actualizarTXT(String r);
    public void actualizarObject(String r);
    public void actualizarXML(String r);
    public void actualizarAleatorio(String r);
    public void actualizarData(String r);
    
    
}
