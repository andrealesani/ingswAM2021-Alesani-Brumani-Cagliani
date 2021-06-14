package model.lorenzo.tokens;

import model.Color;
import model.resource.ResourceType;

/**
 * Enum used to indicate the type of a Lorenzo single player action token
 */
public enum LorenzoTokenType {
    SingleFaith { @Override public String toString() { return Color.RESOURCE_STD + "SINGLE_FAITH" + Color.RESET; }},
    DoubleFaith { @Override public String toString() { return Color.RESOURCE_STD + "DOUBLE_FAITH" + Color.RESET; }},
    RemoveBlue { @Override public String toString() { return Color.RESOURCE_STD + "REMOVE_BLUE" + Color.RESET; }},
    RemoveGreen { @Override public String toString() { return Color.RESOURCE_STD + "REMOVE_GREEN" + Color.RESET; }},
    RemovePurple { @Override public String toString() { return Color.RESOURCE_STD + "REMOVE_PURPLE" + Color.RESET; }},
    RemoveYellow { @Override public String toString() { return Color.RESOURCE_STD + "REMOVE_YELLOW" + Color.RESET; }};

    public String iconPrint() {
        if (this == LorenzoTokenType.DoubleFaith) {
            return "+2" + Color.RED_LIGHT_FG + "┼" + Color.RESET;
        }
        if (this == LorenzoTokenType.SingleFaith) {
            return "+1" + Color.RED_LIGHT_FG + "┼" + Color.RESET + "««";
        }
        if (this == LorenzoTokenType.RemoveBlue) {
            return "-2" + Color.LIGHT_BLUE_FG + "█" + Color.RESET;
        }
        if (this == LorenzoTokenType.RemoveGreen) {
            return "-2" + Color.GREEN_FG + "█" + Color.RESET;
        }
        if (this == LorenzoTokenType.RemoveYellow) {
            return "-2" + Color.YELLOW_LIGHT_FG + "█" + Color.RESET;
        }
        if (this == LorenzoTokenType.RemovePurple) {
            return "-2" + Color.PURPLE_FG + "█" + Color.RESET;
        }
        // This should never happen
        return null;
    }

    public String getTokenImage() {
        if (this == LorenzoTokenType.DoubleFaith) {
            return "token_2faith.png";
        }
        if (this == LorenzoTokenType.SingleFaith) {
            return "token_1faith_reset.png";
        }
        if (this == LorenzoTokenType.RemoveBlue) {
            return "token_blue.png";
        }
        if (this == LorenzoTokenType.RemoveGreen) {
            return "token_green.png";
        }
        if (this == LorenzoTokenType.RemoveYellow) {
            return "token_yellow.png";
        }
        if (this == LorenzoTokenType.RemovePurple) {
            return "token_purple.png";
        }
        // This should never happen
        return null;
    }
}
