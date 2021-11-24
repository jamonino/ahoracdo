package com.albares.ahorcado.api;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.StringUtils;


public class Match{
    
    private final TreeMap<Integer, User> gamers = new TreeMap();
    private Integer turn = 1;
    private Integer randomNumber;
    private final String[] words = {"AHORCADO","FELIZ","CIEZA"};
    
    private String mask;
    
    
    private final AtomicInteger idGamers = new AtomicInteger(0);
    


    public Match() {
    }
    
    
    public int addUser(User user){
        int id = idGamers.incrementAndGet();
        this.gamers.put(id, user);
        return id;
    }

    public Map<Integer, User> getUsers() {
        return gamers;
    }

    public Integer getTurn() {
        return turn;
    }
    
    public void generateNewMatch(){
        this.randomNumber = (int) (Math.random() * this.words.length);
        this.setMask(StringUtils.repeat("_", this.getRandomWord().length()));
    }

    public Integer getRandomNumber() {
        return randomNumber;
    }
    
    public String getRandomWord() {
        return this.words[this.randomNumber];
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }
    
    public Integer checkChar(char letter){
        Integer coincidences = 0;
        StringBuilder newMask = new StringBuilder(this.mask);
        for (int i = 0; i < this.mask.length(); i++) {
            if(this.mask.charAt(i) == '_' && this.getRandomWord().charAt(i) == letter ){
                newMask.setCharAt(i, letter);
                coincidences++;
            }
        }
        this.mask = newMask.toString();
        return coincidences;
    }
    
    
    public void nextTurn(){
        //SOLUCION 1
        //this.turn++;
        //if(turn > this.gamers.size()) turn = 1;
        
        //SOLUCION 2
        //this.turn = ((turn+1)%this.gamers.size())+1;
        
        //SOLUCION 3  <-- Esta solución permite borrar usuarios sin problema
        this.turn = this.gamers.higherKey(turn);
        if(this.turn==null) this.turn = this.gamers.firstKey();
    }
}
