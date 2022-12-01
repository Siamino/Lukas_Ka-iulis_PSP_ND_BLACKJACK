public class Card {
    private Type type;
    private TypeScore typeScore;

    public Card(Type type, TypeScore typeScore) {
        this.type = type;
        this.typeScore = typeScore;
    }

    public Card(Card card) {
        this.type = card.getType();
        this.typeScore = card.getCardScore();
    }

    public int getScore() {
        return typeScore.score;
    }

    public Type getType() {
        return type;
    }

    public TypeScore getCardScore() {
        return typeScore;
    }

    public String toString() {
        return ("["+typeScore+" of "+ type + "] ("+this.getScore()+")");
    }
}
