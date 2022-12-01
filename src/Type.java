public enum Type {
    CLUB("Clubs"),
    DIAMOND("Diamonds"),
    HEART("Hearts"),
    SPADE("Spades");
    String typeName;
    Type(String typeName) {
        this.typeName = typeName;
    }

    public String toString() {
        return typeName;
    }
}
