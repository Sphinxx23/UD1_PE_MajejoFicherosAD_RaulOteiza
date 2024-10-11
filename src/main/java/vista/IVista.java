package vista;

import controlador.Controlador;

public interface IVista<T> {

    public abstract T getObject();
    public abstract void setObject(T Jugador);
    
     public abstract String showRut();
    public abstract int showPri();
    public abstract void show(int num, String rut);
    
    public abstract void setControlador(Controlador c);

    public void mensaje(String mensaje);
    
}
