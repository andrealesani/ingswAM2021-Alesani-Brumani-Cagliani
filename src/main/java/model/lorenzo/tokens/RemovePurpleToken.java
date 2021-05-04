package model.lorenzo.tokens;

import model.CardColor;
import model.CardTable;
import network.beans.LorenzoTokenType;

public class RemovePurpleToken extends RemoveCardsToken{
    public RemovePurpleToken(CardTable cardTable) {
        super(CardColor.PURPLE, cardTable, LorenzoTokenType.RemovePurple);
    }
}
