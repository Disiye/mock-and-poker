package poker;

import java.util.Objects;

public class Card {
    private String num;
    private String color;

    public Card(String num, String color) {
        this.num = num;
        this.color = color;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(num, card.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }
}
