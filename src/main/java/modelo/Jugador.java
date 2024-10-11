/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Vespertino
 */
public class Jugador implements Serializable{
    private int user_id;
    private String nick_name;
    private int experience;
    private int life_level;
    private int coins;

    public Jugador(int user_id, String nick_name, int experience, int life_level, int coins) {
        this.user_id = user_id;
        this.nick_name = nick_name;
        this.experience = experience;
        this.life_level = life_level;
        this.coins = coins;
    }

    public Jugador() {       
        this(00, "None", 0, 0, 0);
    }
    
   /**
     * Devuelve el id
     * return el id asignado como atributo
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Settea el id
     * param el id que asignamos como atributo
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Devuelve el nick del jugador
     * return el nombre asignado como atributo
     */
    public String getNick_name() {
        return nick_name;
    }

    /**
     * Settea el nick
     * param el nombre que asignamos como atributo
     */
    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    /**
     * Devuelve la experiencia del jugador
     * return el numerode xp asignado como atributo
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Settea la xp 
     * param el numero de xp que asignamos como atributo
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Devuelve las monedas del jugador
     * return el numero de monedas
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Settea las monedas 
     * param el numero de monedas 
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * Devuelve las vidas del jugador
     * return el numero de vidas
     */
    public int getLife_level() {
        return life_level;
    }

    /**
     * Settea las vidas 
     * param el numero de vidas 
     */
    public void setLife_level(int life_level) {
        this.life_level = life_level;
    }
    
    
    /**
     * Devuelve todos los datos del jugador de una forma determinada
     * return datos de jugador con formato 
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[USER_ID = "+this.user_id +", NICK_NAME = " + this.nick_name +", EXPERIENCE = " + this.experience +", LIFE_LEVEL = " + this.life_level +", COINS = " + this.coins + "]");
        return sb.toString();
    }
        
}
