package controlador;

import DAO.IDAO;
import modelo.Jugador;
import vista.IVista;

public class Controlador {

    private IVista<Jugador> vista;

    private IDAO<Jugador> dao;

    public Controlador(IVista vista) {
        this.vista = vista;
    }

    /**
     * Settea el modelo param el modelo dao
     */
    public void setModelo(IDAO<Jugador> modelo) {
        this.dao = modelo;
    }

    /**
     * Realiza la operacion elegida en el menu y sale un mensaje si se realiza
     * bien o no param el numero de la operacion deseada
     */
    public void operacion(int op) {
        String mensaje;
        Jugador aux;
        switch (op) {
            case 1: //insercion
                aux = vista.getObject();
                mensaje = dao.insertar(aux);
                if (mensaje == null) {
                    vista.mensaje("La insercion se ha hecho correctamente");
                } else {
                    vista.mensaje(mensaje);
                }
                break;

            case 2: //eliminacion
                aux = vista.getObject();
                mensaje = dao.borrar(aux);
                if (mensaje == null) {
                    vista.mensaje("El borrado se ha hecho correctamente");
                } else {
                    vista.mensaje(mensaje);
                }
                break;

            case 3: //modificacion
                aux = vista.getObject();
                mensaje = dao.modificar(aux);
                if (mensaje == null) {
                    vista.mensaje("La modficacion se ha hecho correctamente");
                } else {
                    vista.mensaje(mensaje);
                }
                break;

            case 4: //consulta
                aux = vista.getObject();
                System.out.println(dao.consultarPorId(aux));
                break;
            case 5: //listar                
                System.out.println(dao.listar());
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * LLama al metodo de creacion del tipo de archivo deseado seleccionado en el menu 
     * param el numero del tipo de archivo deseado y la ruta completa de donde alojar los archivos
     */
    public void eleccion(int num, String rut) {
        switch (num) {
            case 1:
                dao.actualizarTXT(rut);
                break;
            case 2:
                dao.actualizarData(rut);
                break;
            case 3:
                dao.actualizarObject(rut);
                break;
            case 4:
                dao.actualizarAleatorio(rut);
                break;
            case 5:
                dao.actualizarXML(rut);
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Reinicia la lista de jugadores  
     */
    public void reiniciarLista() {
        dao.reinicio();
    }

}
