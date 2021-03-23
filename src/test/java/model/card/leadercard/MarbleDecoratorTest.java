package model.card.leadercard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarbleDecoratorTest {

    @Test
    void doAction() {
        LeaderCard leaderCard1 = new MarbleDecorator(new LeaderCardImpl());

        leaderCard1.doAction();
    }
}