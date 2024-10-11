/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.Jugador;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class JugadorDaoLinkedList implements IDAO<Jugador> {

    private LinkedList<Jugador> hs;

    public JugadorDaoLinkedList() {
        hs = new LinkedList<Jugador>();
    }

    
    
    /**
     * Inserta un jugador en la lista 
     * param el jugador a insertar
     * return un mensjae de confirmacion si se ha realizado correctamente
     */
    @Override
    public String insertar(Jugador o) {

        for (Jugador h : hs) {
            if (h.getUser_id() == o.getUser_id()) {

                return "Ese jugador ya esta dentro de la lista";
            }
        }
        hs.add(o);
        return "Jugador añadido correctamente";
    }

    /**
     * Modifica un jugador en la lista 
     * param el jugador a modificar
     * return un mensjae de confirmacion si se ha realizado correctamente
     */
    @Override
    public String modificar(Jugador nuevo) {

        for (Jugador p : hs) {

            if (p.getUser_id() == nuevo.getUser_id()) {

                p.setNick_name(nuevo.getNick_name());
                p.setExperience(nuevo.getExperience());
                p.setCoins(nuevo.getCoins());
                p.setLife_level(nuevo.getLife_level());
                return "Datos nuevos guardados";
            }
        }
        return "No existe el jugador a modificar";
    }

    /**
     * Borra un jugador de la lista 
     * param el jugador a borrar
     * return un mensjae de confirmacion si se ha realizado correctamente
     */
    @Override
    public String borrar(Jugador viejo) {
        for (Jugador h : hs) {
            if (h.getUser_id() == viejo.getUser_id()) {
                hs.remove(h);
                return "Borrado con exito";
            }
        }
        return "No existe el jugador";
    }

    /**
     * Consulta un jugador de la lista 
     * param el jugador a consultar
     * return datos del jugador a consultar o un mensaje de fallo
     */
    @Override
    public String consultarPorId(Jugador o) {
        StringBuilder sb = new StringBuilder();

        for (Jugador h : hs) {
            if (h.getUser_id() == o.getUser_id()) {
                sb.append(h.toString());
                return sb.toString();
            }
        }
        return "No existe jugador con ese ID";
    }

    /**
     * Muestra la lista con todos los jugadores
     * return datos de todos los jugadores de la lista o si esta está vacia
     */
    @Override
    public String listar() {
        StringBuilder sb = new StringBuilder();
        if (!hs.isEmpty()) {
            for (Jugador h : hs) {
                sb.append(h.toString() + "\n");
            }
            return sb.toString();
        } else {
            return "Lista vacia";
        }

    }

    /**
     * Borra el xml si ya existe y crea uno con el mismo nombre en el que introduce todos los jugadores de la lista y sus datos 
     * param la ruta de la carpeta creada
     */
    @Override
    public void actualizarXML(String rut) {
        try {

            File archivo = new File(rut + "\\", "jugadores.xml");
            if (archivo.exists()) {
                archivo.delete();
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("jugadores");
            doc.appendChild(rootElement);

            // Recorrer la lista de jugadores para añadir 
            for (Jugador jugador : hs) {
                Element jugadorElement = doc.createElement("jugador");
                rootElement.appendChild(jugadorElement);

                Element userId = doc.createElement("user_id");
                userId.appendChild(doc.createTextNode(String.valueOf(jugador.getUser_id())));
                jugadorElement.appendChild(userId);

                Element nickName = doc.createElement("nick_name");
                nickName.appendChild(doc.createTextNode(jugador.getNick_name()));
                jugadorElement.appendChild(nickName);

                Element experience = doc.createElement("experience");
                experience.appendChild(doc.createTextNode(String.valueOf(jugador.getExperience())));
                jugadorElement.appendChild(experience);

                Element coins = doc.createElement("coins");
                coins.appendChild(doc.createTextNode(String.valueOf(jugador.getCoins())));
                jugadorElement.appendChild(coins);

                Element lifeLevel = doc.createElement("life_level");
                lifeLevel.appendChild(doc.createTextNode(String.valueOf(jugador.getLife_level())));
                jugadorElement.appendChild(lifeLevel);
            }

            // Guardar XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(rut + "\\" + "jugadores.xml"));

            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Borra el txt si ya existe y crea uno con el mismo nombre en el que introduce todos los jugadores de la lista y sus datos 
     * param la ruta de la carpeta creada
     */
    @Override
    public void actualizarTXT(String rut) {
        try {

            File archivo = new File(rut + "\\", "jugadores.txt");
            if (archivo.exists()) {
                archivo.delete();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
            char[] cad;

            for (Jugador jugador : hs) {

                cad = jugador.toString().toCharArray();

                for (int i = 0; i < cad.length; i++) {
                    writer.write(cad[i]);
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Borra el dat si ya existe y crea uno con el mismo nombre en el que introduce
     * todos los jugadores de la lista y sus datos con ObjectOutputStream
     * param la ruta de la carpeta creada
     */
    @Override
    public void actualizarObject(String rut) {
        try {
            File archivo = new File(rut + "\\", "object.dat");
            if (archivo.exists()) {
                archivo.delete();
            }

            //crear flujo de salida
            ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(archivo));

            for (Jugador jugador : hs) {

                objectOut.writeObject(jugador + "\n");
            }

            objectOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Borra el dat si ya existe y crea uno con el mismo nombre en el que introduce
     * todos los jugadores de la lista y sus datos con RandomAccessFile
     * param la ruta de la carpeta creada
     */
    @Override
    public void actualizarAleatorio(String rut) {
        try {
            File archivo = new File(rut + "\\", "aleatorio.dat");
            if (archivo.exists()) {
                archivo.delete();
            }

            RandomAccessFile rand = new RandomAccessFile(archivo, "rw");
            for (Jugador jugador : hs) {
                rand.writeUTF(jugador.toString() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Borra el dat si ya existe y crea uno con el mismo nombre en el que introduce
     * todos los jugadores de la lista y sus datos con DataOutputStream
     * param la ruta de la carpeta creada
     */
    @Override
    public void actualizarData(String rut) {

        try {
            File archivoDat = new File(rut + "\\", "datos.dat");
            
            if (archivoDat.exists()) {
                archivoDat.delete();
            }
            
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivoDat));

            for (Jugador jugador : hs) {
                dos.writeUTF(jugador.toString() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reinicia todos los datos de la lista
     */
    @Override
    public void reinicio() {
        hs.clear();
    }

}
