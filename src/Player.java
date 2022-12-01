import java.util.Scanner;

public class Player extends Person{
    Scanner decision = new Scanner(System.in);
    public Player() {
        super.setName("Player");
    }

    public void makeDecision(Deck deck, Deck discard) {
        int chosen = 0;
        boolean getNum = true;

        while(getNum) {
            try {
                System.out.println("1) Pull or 2) Stand");
                chosen = decision.nextInt();
                getNum = false;
            } catch (Exception e) {
                System.out.println("Wrong choice");
                decision.next();
            }
        }
        if(chosen == 1) {
            this.hit(deck,discard);
            if(this.getHand().calculatedScore() > 20) {
            }
            else {
                this.makeDecision(deck,discard);
            }

        } else {
            System.out.println("You Stand.\n");
        }
    }
}
