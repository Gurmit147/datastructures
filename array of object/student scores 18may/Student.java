import java.util.Scanner;
public class Student {
    public static void main(String args[]){
        String name;
        float score=0;
        float totalscores = 0;
        float avgscores=0;
        float lessavgscores = 0;
        float moreavgscores = 0;
        Scanner sc = new Scanner(System.in);
        Scores scores[] = new Scores[20];

        for(int i=0; i<scores.length;i++){
            System.out.print("Enter name:");
            name = sc.next()+sc.nextLine();
            System.out.print("Enter score:");
            score = sc.nextInt();

            scores[i] = new Scores(name,score);

        }

        System.out.println("Display scores:");
        for(int i=0; i<scores.length; i++){
            scores[i].displayscores();
        }
        
        System.out.println("Average scores:");
        for(int i=0; i<scores.length; i++){
            totalscores += scores[i].getScores();
            avgscores = totalscores/scores.length;
        }
        System.out.println(avgscores);

        System.out.println("Number of students with lower than the average score:");
        for(int i=0; i<scores.length; i++){
            if(scores[i].getScores()< avgscores){
                lessavgscores ++;
            }
        }
        System.out.println(lessavgscores);

        System.out.println("Number of students with higher than the average score:");
        for(int i=0; i<scores.length; i++){
            if(scores[i].getScores()> avgscores){
                moreavgscores ++;
            }
        }
        System.out.println(moreavgscores);


    }
}

class Scores{
    String name;
    float scores;

    Scores(String name, float scores){
        this.name = name;
        this.scores = scores;
    }

    public void displayscores(){
        System.out.println("Name: "+ name + " Scores: " + scores);
    }

    public float getScores(){
        return scores;
    }
}
