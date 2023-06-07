/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

/**
 *
 * @author Ziqi
 */
public class Character {

    private String type;
    private int initialHealth;
    private int currentHealth;
    private int attack;

    public Character(String type, int initialHealth, int attack) {
        this.type = type;
        this.initialHealth = initialHealth;
        this.currentHealth = initialHealth;
        this.attack = attack;
    }

    //getter
    public String getType() {
        return type;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    //setter
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getAttack() {
        return attack;
    }

    //It represents the new health value of the target character after the attack.
    public void attack(Character target) {
        target.setCurrentHealth(target.getCurrentHealth() - attack);
    }
}
