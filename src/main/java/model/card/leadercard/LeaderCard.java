package model.card.leadercard;

public interface LeaderCard {
    void doAction(); /* this method should either be boolean or throw an exception */
    boolean areRequirementsMet();
}