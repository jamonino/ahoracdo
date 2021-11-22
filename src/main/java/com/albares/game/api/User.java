
package com.albares.game.api;

public class User {
    private String name;
    private Integer score = 0;

    public User() {
    }
    
    public void increment(Integer quantity) {
        this.score+= quantity;
    }

    public void decrement(Integer quantity) {
        this.score -= quantity;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}
