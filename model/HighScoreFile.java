package model;

import java.io.*;

public class HighScoreFile {
    protected int easyScore;
    protected int medScore;
    protected int hardScore;
    protected int[] allScores = new int[6];

    public HighScoreFile() {
    }

    public int getScore(int dif) {
        if (dif == 3) {
            return hardScore;
        } else if (dif == 2) {
            return medScore;
        } else {
            return easyScore;
        }
    }

    public int[] getScores() {
        return allScores;
    }

    public void setScore(int dif, int score) {
        //grab newScore from Timer class
        if (dif == 3) {
            if (hardScore > score)
            {
                hardScore = score;
            }
        } else if (dif == 2) {
            if (medScore > score)
            {
                medScore = score;
            }
        } else {
            if (easyScore > score) {
                easyScore = score;
            }
        }
    }

    public void loadScores() {
        File file = new File("scores.txt");
        String[] tempScores = new String[6];
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                tempScores = (br.readLine()).split("\\s+");
            } catch (IOException e) {
                System.out.println("ERROR: Couldn't read the scores from the file!");
            }
        } else {
            try {
                FileWriter fstream = new FileWriter("scores.txt");
                BufferedWriter out = new BufferedWriter(fstream);
                out.write("999 999 999 999 999 999");
                out.close();
                tempScores = new String[]{"999", "999", "999", "999", "999", "999"};
            } catch (IOException e) {
                System.out.println("ERROR: Couldn't initialize scores file!");
            }
        }

        for (int i = 0; i < 6; i++) {
            allScores[i] = Integer.parseInt(tempScores[i]);
        }

        easyScore = (Integer.parseInt(tempScores[0])*60)+Integer.parseInt(tempScores[1]);
        medScore = (Integer.parseInt(tempScores[2])*60)+Integer.parseInt(tempScores[3]);
        hardScore = (Integer.parseInt(tempScores[4])*60)+Integer.parseInt(tempScores[5]);
    }

    public void saveScores() {
        FileWriter fstream;
        try {
            fstream = new FileWriter("scores.txt");
            BufferedWriter out = new BufferedWriter(fstream);

            allScores[0] = easyScore/60;
            allScores[1] = easyScore%60;
            allScores[2] = medScore/60;
            allScores[3] = medScore%60;
            allScores[4] = hardScore/60;
            allScores[5] = hardScore%60;

            String easy = (allScores[0] + " "+String.format("%02d", allScores[1]));
            String med = (allScores[2] + " "+String.format("%02d", allScores[3]));
            String hard = (allScores[4] + " "+String.format("%02d", allScores[5]));
            out.write(easy+" "+med+" "+hard);
            out.close();
        } catch (IOException e) {
            System.out.println("ERROR: Couldn't save score!");
        }
    }
}
