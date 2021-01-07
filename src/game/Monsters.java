package game;

import java.util.Random;

public class Monsters {
    private String monstersName;
    private int monstersHealth;
    private int monstersAttack ;
    private int monstersDamage;
    private boolean isAlive = true;

    public Monsters(String monstersName, int monstersHealth, int monstersAttack) {
        this.monstersName = monstersName;
        this.monstersHealth = monstersHealth;
        this.monstersAttack = monstersAttack;
        monstersDamage   = new java.util.Random().nextInt(monstersAttack)+1;
    }

    public Monsters() {        

        final String[] proper_class = {"Skeleton", "Goblin", "Zombie", "Spider"};
        final String[] proper_noun = {"Fred", "Jane", "Nixon", "Clinton", "Baiden"};
        
        Random random = new Random();

        this.monstersAttack = 1 + random.nextInt(5);
        this.monstersHealth = 10 + random.nextInt(10);

        int index = random.nextInt(proper_class.length);
        this.monstersName = proper_class[index];        

        index = random.nextInt(proper_noun.length);        
        this.monstersName += " ";
        this.monstersName += proper_noun[index];

    }

    public String getMonstersName() {
        return monstersName;
    }

    public void setMonstersName(String monstersName) {
        this.monstersName = monstersName;
    }

    public int getMonstersHealth() {
        return monstersHealth;
    }

    public void setMonstersHealth(int monstersHealth) {
        this.monstersHealth = monstersHealth;
    }

    public int getMonstersAttack() {
        return monstersAttack;
    }

    public void setMonstersAttack(int monstersAttack) {
        this.monstersAttack = monstersAttack;
    }

    public int getMonstersDamage() {
        return monstersDamage;
    }

    public void markDead() {
        this.isAlive = false;
    }

    public boolean isDead() {
        return isAlive == false; 
    }
}
