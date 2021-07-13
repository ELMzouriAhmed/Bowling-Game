
import org.game.bowling.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartGame {


    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        List<Frame> frames = new ArrayList<>();
        String firstTry;
        String secondeTry;

        for (int i = 0; i < 10; i++) {
            System.out.print("saisir votre premiere shot : ");
            firstTry = clavier.nextLine();
            System.out.print("saisir votre desieme shot :  ");
            secondeTry = clavier.nextLine();
            frames.add(checkTriesFor(firstTry, secondeTry, i));
        }
        Line line = new Line(frames);
        System.out.println("Score finale = " + line.getScore());

    }


    private static Frame checkTriesFor(String firstTry, String secondTry, int index) {
        if (StrikeFrame.checkFirstTry(Roll.of(firstTry))) {
            return StrikeFrame.checkFrame(firstTry, secondTry, index);
        }
        if (SpareFrame.checkSecondTry(Roll.of(secondTry))) {
            return SpareFrame.checkFrame(firstTry, secondTry, index);
        }
        return OpenFrame.checkFrame(firstTry, secondTry);
    }


}
