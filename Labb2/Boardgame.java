public interface Boardgame {

    /**Metoden move anropas och draget utförs. Vid ett genomfört drag så returneras true och vid ett drag som inte går att genomföra returnerar metoden false
    @param r row of the board (with index from 0)
    @param c column of the board (with index from 0)
    @return Boolean som representerar om draget gick att genomföra*/
    public boolean move(int x, int y);

    /**Metoden för att läsa av splets ställning, position för position
    @param r row of the board (with index from 0)
    @param c column of the board (with index from 0)
    @return String with a message*/
    public String getStatus(int x, int y);

    /**Ger ett aktuellt meddelande som säger om draget gick bra eller ej. Vid felaktigt drag ges hjälpsam information.
    @return String med hjälpsam information*/
    public String getMessage();
}