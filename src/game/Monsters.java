package game;

public class Monsters {
    private String monstersName;
    private int monstersHealth;
    private int monstersAttack ;
    private int monstersDamage;

    public Monsters(String monstersName, int monstersHealth, int monstersAttack) {
        this.monstersName = monstersName;
        this.monstersHealth = monstersHealth;
        this.monstersAttack = monstersAttack;
        monstersDamage   = new java.util.Random().nextInt(monstersAttack)+1;
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
}
