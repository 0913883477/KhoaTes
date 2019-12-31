import java.io.*;
import java.util.logging.Logger;

public class Level {
    static String lvl = "1";
    static String [][] Level = {{"NormalZombie"},{"NormalZombie","ConeHeadZombie"}};
    static int [][][] LevelValue = {{{0,99}},{{0,49},{50,99}}} ;

    public Level() {
        try {
            File f = new File("Level.vbhv");

            if (!f.exists()) {
                BufferedWriter bwr = new BufferedWriter(new FileWriter(f));
                bwr.write("1");
                bwr.close();
                lvl = "1";
            } else {
                BufferedReader br = new BufferedReader(new FileReader(f));
                lvl = br.readLine();
            }
        } catch (Exception ex) {

        }
    }
            public static void write (String lvl){
                File f = new File("Level.vbhv");
                try {
                    BufferedWriter bwr = new BufferedWriter(new FileWriter(f));
                    bwr.write(lvl);
                    bwr.close();
                    lvl = lvl;
                } catch (IOException ex) {
                    Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }

            }
        }
