import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {
    private static GameCharacter player ;

    private JFrame gameGUI;
    private Container gameUI;
    private String name;
    Monster monster;

    private JPanel status;
    private JPanel command;
    private JProgressBar monsterhealthpointbar;

    JButton statusbt;

    public GameGUI(){

        gameGUI = new JFrame();
        gameGUI.setTitle("Pioneer");
        gameGUI.setSize(600,400);
        gameGUI.setLocationRelativeTo(null);
        gameGUI.setResizable(false);
        gameGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameUI = gameGUI.getContentPane();
        gameUI.setLayout(new BorderLayout());
    }

    public void createCharacter(){
        name = JOptionPane.showInputDialog("Enter you character name");
    }

    public void job(){
        Object[] options = {"Warrior", "Theif"};
        int n = JOptionPane.showOptionDialog(null, "Choose your job", "Job", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (n == 0){
            player = new Warrior(name);
        }
        else if (n == 1){
            player = new Theif(name);
        }
    }

    public void showStatus(){
        JOptionPane statusDialog = new JOptionPane();
        String text =   "NAME : "+player.getName()+"\n"+
                        "LV : "+player.getLevel()+"\n"+
                        "EXP : "+player.getExp()+"/"+player.getMaxExp()+"\n"+
                        "HP : "+player.getHp()+"/"+player.getMaxHp()+"\n"+
                        "Damage : "+player.getDamage()+"\n"+
                        "Monsterkill : "+ player.getKillMonster()+ " Kill";


        JOptionPane.showMessageDialog(statusDialog,text,"Status",JOptionPane.INFORMATION_MESSAGE);
    }

    public void makeGUI(){

        JLabel namelb = new JLabel(player.getName().toUpperCase() + "                Monster kill : " +player.getKillMonster() + " Kill");

        status = new JPanel();
        status.setLayout(new BoxLayout(status,BoxLayout.Y_AXIS));
        namelb.setFont(new Font("Serif",Font.PLAIN,20));

        JProgressBar playerhealthpointbar = new JProgressBar();
        playerhealthpointbar.setString("HP : "+ player.getHp()+"/"+ player.getMaxHp());
        playerhealthpointbar.setMaximum(player.getMaxHp());
        playerhealthpointbar.setForeground(Color.RED);
        playerhealthpointbar.setStringPainted(true);
        playerhealthpointbar.setValue(player.getHp());
        playerhealthpointbar.setMaximum(player.getMaxHp());

        JProgressBar expbar = new JProgressBar();
        expbar.setString("EXP : "+ player.getExp()+"/"+ player.getMaxExp());
        expbar.setMaximum(player.getMaxExp());
        expbar.setForeground(Color.ORANGE);
        expbar.setStringPainted(true);
        expbar.setValue(player.getExp());

        monsterhealthpointbar = new JProgressBar();

        statusbt = new JButton();
        statusbt.setPreferredSize(new Dimension(130,30));
        statusbt.setText("Show Status");

        statusbt.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent actionEvent) {
                showStatus();
            }
        });

        JButton usebagbt = new JButton();
        usebagbt.setPreferredSize(new Dimension(130,30));
        usebagbt.setText("Open Bag");
        usebagbt.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent actionEvent) {
                Object[] possibilities = {"Potion", "Super Potion"};
                String text = player.getBag().potion.getItemName() + " (" + player.getBag().potion.getHealPoint() +") : " + player.getBag().potion.getHaveit()+"\n" +
                              player.getBag().superPotion.getItemName() + " (" + player.getBag().superPotion.getHealPoint() +") : " + player.getBag().superPotion.getHaveit();
                String s = (String)JOptionPane.showInputDialog(null, text, "Bag", JOptionPane.PLAIN_MESSAGE, null, possibilities, "Potion");
                if(s == "Potion"){
                    if(player.setHp(player.getBag().potion.getHealPoint())){
                        player.getBag().potion.setHaveit(-1);
                    }
                }
                else if(s == "Super Potion"){
                    if(player.setHp(player.getBag().superPotion.getHealPoint())){
                        player.getBag().superPotion.setHaveit(-1);
                    }
                }
                makeGUI();
            }
        });
        
        JButton attackmonsterbt = new JButton();
        attackmonsterbt.setPreferredSize(new Dimension(130,30));
        attackmonsterbt.setText("Attack monster");

        attackmonsterbt.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent actionEvent) {
                attackMonster();
                monsterhealthpointbar.setString("MONSTER : "+ monster.getHp()+"/"+ monster.getMaxHp());
                monsterhealthpointbar.setMaximum(monster.getMaxHp());
                monsterhealthpointbar.setForeground(Color.BLUE);
                monsterhealthpointbar.setStringPainted(true);
                monsterhealthpointbar.setValue(monster.getHp());
                makeGUI();
            }
        });

        status.add(namelb);
        status.add(playerhealthpointbar);
        status.add(expbar);

        command = new JPanel();
        command.setLayout(new FlowLayout());
        command.add(statusbt);
        command.add(usebagbt);
        command.add(attackmonsterbt);

        gameUI.add(status,BorderLayout.NORTH);
        gameUI.add(command,BorderLayout.CENTER);
        gameUI.add(monsterhealthpointbar,BorderLayout.SOUTH);

        gameGUI.setVisible(true);
    }

    private void attackMonster(){
        monster = new Monster("monster",100,20);
        player.attack(monster.getDamage());
        player.setExp(2);
        player.setKillMonster();
        if(player.isDead()){
            System.exit(0);
        }
    }
}