package poker;

import java.util.List;

public class PokerHands {
    //constant for number of cards in the hand
    private final int TOTAL_CARDS = 5;
    //instance data
    private int[] cardsValues = new int[TOTAL_CARDS];
    private char[] cardsSuits = new char[TOTAL_CARDS];
    //rank goes from 0 (high card) to 8(straight flush)
    private int rank;

    public PokerHands(String[] cards) {
        //assume the array of cards is sorted from least to greatest
        //split cards into Values and Suits arrays
        for (int i = 0; i < TOTAL_CARDS; i++) {
            if (cards[i].charAt(0) == 'T')
                cardsValues[i] = 10;
            else if (cards[i].charAt(0) == 'J')
                cardsValues[i] = 11;
            else if (cards[i].charAt(0) == 'Q')
                cardsValues[i] = 12;
            else if (cards[i].charAt(0) == 'K')
                cardsValues[i] = 13;
            else if (cards[i].charAt(0) == 'A')
                cardsValues[i] = 14;
            else
                cardsValues[i] = Integer.parseInt(cards[i].substring(0, 1));

            cardsSuits[i] = cards[i].charAt(1);
        }
        //assign a rank to the hand of cards
        this.rank = calculateRank();
    }

    private int calculateRank() {
        //check for rank 8: Straight Flush
        boolean flush = false;
        boolean straight = false;
        //check for Flush
        if (cardsSuits[0] == cardsSuits[1]
                && cardsSuits[1] == cardsSuits[2]
                && cardsSuits[2] == cardsSuits[3]
                && cardsSuits[3] == cardsSuits[4])
            flush = true;
        //check for Straight
        if ((cardsValues[0] + 1) == cardsValues[1]
                && (cardsValues[1] + 1) == cardsValues[2]
                && (cardsValues[2] + 1) == cardsValues[3]
                && (cardsValues[3] + 1) == cardsValues[4])
            straight = true;
        if (flush && straight)
            return 8;

        //check for rank 7: Four Of A Kind
        for (int i = 0; i < TOTAL_CARDS; i++) {
            for (int j = 0; j < TOTAL_CARDS; j++) {
                for (int k = 0; k < TOTAL_CARDS; k++) {
                    for (int l = 0; l < TOTAL_CARDS; l++) {
                        if (cardsValues[i] == cardsValues[j] && cardsValues[j] == cardsValues[k] && cardsValues[k] == cardsValues[l]
                                && j != i && i != k && k != j && l != j && l != k && l != i)
                            return 7;
                    }
                }
            }
        }

        //check for rank 6: Full House
        boolean toak = false;
        int toakValue = 0;
        boolean pair = false;
        for (int i = 0; i < TOTAL_CARDS; i++) {
            for (int j = 0; j < TOTAL_CARDS; j++) {
                for (int k = 0; k < TOTAL_CARDS; k++) {
                    if (cardsValues[i] == cardsValues[j] && cardsValues[j] == cardsValues[k] && j != i && i != k && k != j) {
                        toak = true;
                        toakValue = cardsValues[i];
                    }
                }
            }
        }
        for (int i = 0; i < TOTAL_CARDS && !pair; i++) {
            for (int j = 0; j < TOTAL_CARDS; j++) {
                if (cardsValues[i] == cardsValues[j] && cardsValues[i] != toakValue && j != i)
                    pair = true;
            }
        }
        if (toak && pair)
            return 6;


        //check for rank 5: Flush
        if (flush)
            return 5;

        //check for rank 4: Straight
        if (straight)
            return 4;

        //check for rank 3: Three Of A Kind
        if (toak)
            return 3;

        //check for rank 2: Two Pair
        int pair1 = 0;
        for (int i = 0; i < TOTAL_CARDS && pair1 == 0; i++) {
            for (int j = 0; j < TOTAL_CARDS; j++) {
                if (cardsValues[i] == cardsValues[j] && j != i)
                    pair1 = cardsValues[i];
            }
        }
        for (int i = 0; i < TOTAL_CARDS; i++) {
            for (int j = 0; j < TOTAL_CARDS; j++) {
                if (cardsValues[i] == cardsValues[j] && cardsValues[i] != pair1 && j != i)
                    return 2;
            }
        }

        //check for rank 1: Pair
        if (pair)
            return 1;

        //if nothing else, then must be rank 0: high card
        return 0;
    }

    public String beats(PokerHands p2) {
        //find the winner
        if (rank > p2.rank)
            return "Player1 Win";
        else if (rank < p2.rank)
            return "Player2 Win";
        else {
            switch (rank) {
                case 1:
                    return thisBeatsP2InTiePair(p2);
                case 2:
                    return thisBeatsP2InTieTwoPair(p2);
                case 3:
                    return thisBeatsP2InTieThreeOfAKind(p2);
                case 6:
                    return thisBeatsP2InTieThreeOfAKind(p2);    //the three of a kind method works for a full house as well
                case 7:
                    return thisBeatsP2InTieFourOfAKind(p2);
                default:
                    return thisBeatsP2InTieHighCard(p2);        //works for high card, straight, flush, straight flush (rank 0, 4, 5, 8)
            }
        }
    }

    private String thisBeatsP2InTieHighCard(PokerHands p2) {
        for (int i = TOTAL_CARDS - 1; i >= 0; i--) {
            if (cardsValues[i] > p2.cardsValues[i])
                return "Player1 Win";
            if (cardsValues[i] < p2.cardsValues[i])
                return "Player2 Win";
        }
        //should never happen
        return "Player2 Win";
    }

    private String thisBeatsP2InTiePair(PokerHands p2) {
        int p1PairValue = 0;
        int p2PairValue = 0;

        for (int i = 0; i < TOTAL_CARDS && (p1PairValue == 0 || p2PairValue == 0); i++) {
            for (int j = 0; j < TOTAL_CARDS; j++) {
                if (cardsValues[j] == cardsValues[i] && j != i)
                    p1PairValue = cardsValues[j];
                if (p2.cardsValues[j] == p2.cardsValues[i] && j != i)
                    p2PairValue = p2.cardsValues[j];
            }
        }
        if (p1PairValue > p2PairValue)
            return "Player1 Win";
        else if (p1PairValue < p2PairValue)
            return "Player2 Win";
        else {
            //delete the cards that created the pair from the cardsValues array, so not to the count them when finding the high card
            deleteCardValue(p1PairValue);
            deleteCardValue(p2PairValue);
            //find winner by high card
            return thisBeatsP2InTieHighCard(p2);
        }
    }

    private String thisBeatsP2InTieTwoPair(PokerHands p2) {
        int p1Pair1Value = highestPairValue(cardsValues);
        int p2Pair1Value = highestPairValue(p2.cardsValues);

        //check first pair for winner
        if (p1Pair1Value > p2Pair1Value)
            return "Player1 Win";
        if (p1Pair1Value < p2Pair1Value)
            return "Player2 Win";

        //delete the cards that create the pair from the cardsValues array, so not to the count them when finding the next pair
        deleteCardValue(p1Pair1Value);
        deleteCardValue(p2Pair1Value);

        int p1Pair2Value = highestPairValue(cardsValues);
        int p2Pair2Value = highestPairValue(p2.cardsValues);

        //check second pair for winner
        if (p1Pair2Value > p2Pair2Value)
            return "Player1 Win";
        if (p1Pair2Value < p2Pair2Value)
            return "Player2 Win";

        //delete the cards that create the second pair from the cardsValues array, so not to the count them when finding the high card
        deleteCardValue(p1Pair2Value);
        deleteCardValue(p2Pair2Value);

        //find winner by high card
        return thisBeatsP2InTieHighCard(p2);
    }

    private String thisBeatsP2InTieThreeOfAKind(PokerHands p2) {
        //TOAK = Three of a kind
        int p1toak = 0;
        int p2toak = 0;
        for (int i = 0; i < TOTAL_CARDS && (p1toak == 0 || p2toak == 0); i++) {
            for (int j = 0; j < TOTAL_CARDS && (p1toak == 0 || p2toak == 0); j++) {
                for (int k = 0; k < TOTAL_CARDS && (p1toak == 0 || p2toak == 0); k++) {
                    if (cardsValues[i] == cardsValues[j] && cardsValues[j] == cardsValues[k] && j != i && i != k && k != j)
                        p1toak = cardsValues[i];
                    if (p2.cardsValues[i] == p2.cardsValues[j] && p2.cardsValues[j] == p2.cardsValues[k] && j != i && i != k && k != j)
                        p2toak = p2.cardsValues[i];
                }
            }
        }
        //check three of a kind for winner
        if (p1toak > p2toak)
            return "Player1 Win";
        if (p1toak < p2toak)
            return "Player2 Win";

        //delete the cards that create the three of a kind from the cardsValues array, so not to the count them when finding the high card
        deleteCardValue(p1toak);
        deleteCardValue(p2toak);

        //find winner by high card
        return thisBeatsP2InTieHighCard(p2);
    }

    private String thisBeatsP2InTieFourOfAKind(PokerHands p2) {
        //FOAK = Three of a kind
        int p1foak = 0;
        int p2foak = 0;
        for (int i = 0; i < TOTAL_CARDS && (p1foak == 0 || p2foak == 0); i++) {
            for (int j = 0; j < TOTAL_CARDS && (p1foak == 0 || p2foak == 0); j++) {
                for (int k = 0; k < TOTAL_CARDS && (p1foak == 0 || p2foak == 0); k++) {
                    for (int l = 0; l < TOTAL_CARDS && (p1foak == 0 || p2foak == 0); l++) {
                        if (cardsValues[i] == cardsValues[j] && cardsValues[j] == cardsValues[k] && cardsValues[k] == cardsValues[l]
                                && j != i && i != k && k != j && l != j && l != k && l != i)
                            p1foak = cardsValues[i];
                        if (p2.cardsValues[i] == p2.cardsValues[j] && p2.cardsValues[j] == p2.cardsValues[k] && p2.cardsValues[k] == p2.cardsValues[l]
                                && j != i && i != k && k != j && l != j && l != k && l != i)
                            p2foak = p2.cardsValues[i];
                    }
                }
            }
        }
        //check three of a kind for winner
        if (p1foak > p2foak)
            return "Player1 Win";
        if (p1foak < p2foak)
            return "Player2 Win";

        //delete the cards that create the three of a kind from the cardsValues array, so not to the count them when finding the high card
        deleteCardValue(p1foak);
        deleteCardValue(p2foak);

        //find winner by high card
        return thisBeatsP2InTieHighCard(p2);
    }

    private int highestPairValue(int[] cardsValues) {
        //find the value of the highest valued pair
        for (int i = TOTAL_CARDS - 1; i >= 0; i--) {
            for (int j = TOTAL_CARDS - 1; j >= 0; j--) {
                if (cardsValues[i] == cardsValues[j] && cardsValues[i] >= 2 && cardsValues[i] <= 14 && j != i)
                    return cardsValues[i];
            }
        }
        //return 0 if no pairs were found
        return 0;
    }

    private void deleteCardValue(int num) {
        for (int i = 0; i < TOTAL_CARDS; i++) {
            if (cardsValues[i] == num)
                cardsValues[i] = 0;
        }
    }
}
