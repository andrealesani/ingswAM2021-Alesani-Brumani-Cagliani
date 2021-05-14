package network.beans;

import model.CardColor;
import model.Game;
import model.card.DevelopmentCard;
import network.GameController;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CardTableBeanTest {
    @Test
    void test() throws IOException {
        Set<String> list = new HashSet<>();
        list.add("Gigi");
        list.add("Pipino");
        Game game = new Game(list);

        PrintWriter printWriter = new PrintWriter(new StringWriter(), true);
        CardTableBean cardTableBean = new CardTableBean(new GameController("Gigi", printWriter));
        game.getCardTable().addObserver(cardTableBean);

        int i = 0, j;
        for (Map.Entry<CardColor, List<List<DevelopmentCard>>> color : game.getCardTable().getCards().entrySet()) {
            j = 0;
            for (List<DevelopmentCard> deck : color.getValue()) {
                /*assertEquals(game.getCardTable().getTopCardId(deck), cardTableBean.getCards()[i][j]);*/
                j++;
            }
            i++;
        }
    }
}