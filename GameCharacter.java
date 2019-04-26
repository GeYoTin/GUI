public class GameCharacter {

    private String name;
    private int hp;
    private int maxHp;
    private int exp;
    private int maxExp;
    private int level;
    private int damage;
    private Bag bag;
    private int killMonster;

    public GameCharacter(String name, int hp, int damage) {
        this.name = name;
        maxHp = this.hp = hp;
        maxExp = exp = 10;
        level = 1;
        this.damage = damage;
        bag = new Bag();
        killMonster = 0;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMaxExp(){
        return maxExp;
    }

    public int getExp(){
        return exp;
    }

    public Bag getBag(){
        return bag;
    }

    public int getKillMonster(){
        return killMonster;
    }

    public boolean setHp(int hp) {
        this.hp += hp;
        if(this.hp >= maxHp){
            this.hp = maxHp;
            return false;
        }
        else{
            return true;
        }
    }

    public void setKillMonster(){
        killMonster += 1;
    }

    public void setExp(int exp){
        this.exp += exp;
        if(this.exp >= maxExp){
            levelUp();
        }
    }

    public void levelUp(){
        level += 1;
        maxHp += 10;
        hp = maxHp;
        maxExp += 5;
        exp =0;
    }

    public boolean isDead(){
        if(hp == 0)
            return true;
        return false;
    }

    public void attack(int damage){
        this.hp -= damage;
        if(hp < 0){
            hp = 0;
        }
    }
}

class Warrior extends GameCharacter{

    public Warrior(String name){
        super(name, 200, 20);
    }
}

class Theif extends GameCharacter{

    public Theif(String name){
        super(name, 100, 40);
    }
}
