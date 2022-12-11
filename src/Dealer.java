public class Dealer extends Person { //paveldejimas
    public Dealer() {
        super.setName("Dealer");
    }

    public void printHand(){ //Polimorfizmas
        System.out.print("\nThe dealer's hand looks like this: ");
        System.out.println(super.getHand().getCard(0) + " - ###########");
        System.out.println("The second card is face down. \n");
    }
}
