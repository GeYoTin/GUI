public class Item {
    private String itemName;
    private int healPoint;
    private int haveit;

    public Item(String itemName, int healPoint, int haveit) {
        this.itemName = itemName;
        this.healPoint = healPoint;
        this.haveit = haveit;
    }

    public String getItemName() {
        return itemName;
    }

    public int getHealPoint() {
        return healPoint;
    }

    public int getHaveit() {
        return haveit;
    }
    
    public void setHaveit(int n) {
        haveit += n;
    }
}
