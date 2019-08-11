package poker;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PokerHandsTest {

    @Test
    public void givenPlayer1HasHighCard_whenPlayer2HasLowerRankingHighCard_thenPlayer1WinsByHighCard() {
        String[] palyer1 = {"2D", "3C", "TS", "JS", "AH"};
        String[] player2 = {"2H", "3C", "5S", "9C", "KD"};
        PokerHands pokerHandsPlayer1 = new PokerHands(palyer1);
        PokerHands pokerHandsPlayer2 = new PokerHands(player2);

        String result = pokerHandsPlayer1.beats(pokerHandsPlayer2);
        assertEquals("Player1 Win", result);
    }

    @Test
    public void givenPlayer1HasPair_whenPlayer2HasHighCard_thenPlayer1Wins() {
        String[] player1 = {"2D", "3H", "4S", "4C", "AH"};
        String[] player2 = {"2H", "3C", "5S", "9C", "KD"};
        PokerHands pokerHandsPlayer1 = new PokerHands(player1);
        PokerHands pokerHandsPlayer2 = new PokerHands(player2);

        String result = pokerHandsPlayer1.beats(pokerHandsPlayer2);
        assertEquals("Player1 Win", result);
    }

    @Test
    public void givenPlayer1HasPair_whenPlayer2HasLowerRankingPair_thenPlayer1Wins() {
        String[] player1 = {"2D", "3C", "TS", "TC", "QH"};
        String[] player2 = {"2H", "3H", "5S", "6C", "6S"};
        PokerHands pokerHandsPlayer1 = new PokerHands(player1);
        PokerHands pokerHandsPlayer2 = new PokerHands(player2);

        String result = pokerHandsPlayer1.beats(pokerHandsPlayer2);
        assertEquals("Player1 Win", result);
    }

    @Test
    public void givenPlayer1HasPair_whenPlayer2HasSameRankingPair_thenPlayer1WinsByHighCard() {
        String[] player1 = {"2D", "3H", "TS", "TC", "QH"};
        String[] player2 = {"2H", "3S", "5S", "6C", "6D"};
        PokerHands pokerHandsPlayer1 = new PokerHands(player1);
        PokerHands pokerHandsPlayer2 = new PokerHands(player2);

        String result = pokerHandsPlayer1.beats(pokerHandsPlayer2);
        assertEquals("Player1 Win", result);
    }

    @Test
    public void givenPlayer1HasTwoPair_whenPlayer2HasPair_thenPlayer1Wins() {
        String[] player1 = {"7C", "9H", "9S", "QC", "QH"};
        String[] player2 = {"2H", "3S", "5S", "AS", "AC"};
        PokerHands pokerHandsPlayer1 = new PokerHands(player1);
        PokerHands pokerHandsPlayer2 = new PokerHands(player2);

        String result = pokerHandsPlayer1.beats(pokerHandsPlayer2);
        assertEquals("Player1 Win", result);
    }

    @Test
    public void givenPlayer1HasTwoPair_whenPlayer2HasLowerRankingTwoPair_thenPlayer1Wins() {
        String[] player1 = {"7C", "9H", "9S", "KD", "KS"};
        String[] player2 = {"2H", "5H", "5S", "9D", "9C"};
        PokerHands pokerHandsPlayer1 = new PokerHands(player1);
        PokerHands pokerHandsPlayer2 = new PokerHands(player2);

        String result = pokerHandsPlayer1.beats(pokerHandsPlayer2);
        assertEquals("Player1 Win", result);
    }

    @Test
    public void givenPlayer1HasTwoPair_whenPlayer2HasSameRankingTwoPair_thenPlayer1WinsByHighCard() {
        String[] player1 = {"8C", "9H", "9S", "KD", "KS"};
        String[] player2 = {"3C", "5H", "5S", "9D", "9C"};
        PokerHands pokerHandsPlayer1 = new PokerHands(player1);
        PokerHands pokerHandsPlayer2 = new PokerHands(player2);

        String result = pokerHandsPlayer1.beats(pokerHandsPlayer2);
        assertEquals("Player1 Win", result);
    }


    @Test
    public void givenPlayer1HasThreeOfAKind_whenPlayer2HasLowerRankingThreeOfAKind_thenPlayer1Wins() {
        String[] player1 = {"6D", "6H", "6S", "7C", "KH"};
        String[] player2 = {"5C", "5D", "5S", "9C", "JS"};
        PokerHands pokerHandsPlayer1 = new PokerHands(player1);
        PokerHands pokerHandsPlayer2 = new PokerHands(player2);

        String result = pokerHandsPlayer1.beats(pokerHandsPlayer2);
        assertEquals("Player1 Win", result);
    }

    @Test
    public void givenPlayer1HasThreeOfAKind_whenPlayer2HasSameRankingThreeOfAKind_thenPlayer1WinsByHighCard() {
        String[] player1 = {"9C", "JH", "JS", "JD", "QH"};
        String[] player2 = {"2S", "TD", "3S", "3H", "3C"};
        PokerHands pokerHandsPlayer1 = new PokerHands(player1);
        PokerHands pokerHandsPlayer2 = new PokerHands(player2);

        String result = pokerHandsPlayer1.beats(pokerHandsPlayer2);
        assertEquals("Player1 Win", result);
    }

    @Test
    public void givenPlayer1HasStraight_whenPlayer2HasStraight_thenPlayer1WinsByHighCard() {
        String[] player1 = {"9S", "TD", "JC", "QC", "KH"};
        String[] player2 = {"2C", "3H", "4S", "5C", "6D"};
        PokerHands pokerHandsPlayer1 = new PokerHands(player1);
        PokerHands pokerHandsPlayer2 = new PokerHands(player2);

        String result = pokerHandsPlayer1.beats(pokerHandsPlayer2);
        assertEquals("Player1 Win", result);
    }

    @Test
    public void givenPlayer1HasFlush_whenPlayer2HasFlush_thenPlayer1WinsByHighCard() {
        String[] player1 = {"4C", "6C", "7C", "9C", "AC"};
        String[] player2 = {"2H", "3H", "4H", "8H", "TH"};
        PokerHands pokerHandsPlayer1 = new PokerHands(player1);
        PokerHands pokerHandsPlayer2 = new PokerHands(player2);

        String result = pokerHandsPlayer1.beats(pokerHandsPlayer2);
        assertEquals("Player1 Win", result);
    }

    @Test
    public void givenPlayer1HasFullHouse_whenPlayer2HasFullHouse_thenPlayer1Wins() {
        String[] player1 = {"5C", "5H", "5S", "3C", "3H"};
        String[] player2 = {"4H", "4D", "4S", "9S", "9D"};
        PokerHands pokerHandsPlayer1 = new PokerHands(player1);
        PokerHands pokerHandsPlayer2 = new PokerHands(player2);

        String result = pokerHandsPlayer1.beats(pokerHandsPlayer2);
        assertEquals("Player1 Win", result);
    }

    @Test
    public void givenPlayer1HasFourOfAKind_whenPlayer2HasFourOfAKind_thenPlayer1Wins() {
        String[] player1 = {"5D", "5H", "5S", "5C", "2H"};
        String[] player2 = {"4H", "4D", "4S", "4C", "AD"};
        PokerHands pokerHandsPlayer1 = new PokerHands(player1);
        PokerHands pokerHandsPlayer2 = new PokerHands(player2);

        String result = pokerHandsPlayer1.beats(pokerHandsPlayer2);
        assertEquals("Player1 Win", result);
    }

    @Test
    public void givenPlayer1HasStraightFlush_whenPlayer2HasStraightFlush_thenPlayer1WinsByHighCard() {
        String[] player1 = {"5D", "6D", "7D", "8D", "9D"};
        String[] player2 = {"2S", "3S", "4S", "5S", "6S"};
        PokerHands pokerHandsPlayer1 = new PokerHands(player1);
        PokerHands pokerHandsPlayer2 = new PokerHands(player2);

        String result = pokerHandsPlayer1.beats(pokerHandsPlayer2);
        assertEquals("Player1 Win", result);
    }
}
