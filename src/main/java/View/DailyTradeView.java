package View;

/**
 * Created by denizyalcin on 10/06/2017.
 */
public class DailyTradeView {

    public void printInstructionDetails(String settlementDate, double incomingAmount , double outgoingmount){
        System.out.println("***********Trades Settled in " + settlementDate.toString()+"************");
        System.out.println("Incoming Amount in USD: " + incomingAmount);
        System.out.println("Outgoing Amount in USD: " + outgoingmount);
    }
    public void printIncomingHeadlines(){
        System.out.println("***********Ranking of entities in terms of Incoming instructions***********" );
    }
    public void printOutgoingHeadlines(){
        System.out.println("***********Ranking of entities in terms of Outgoing instructions***********" );
    }
    public void printIncomingRanks(int rank, String entityName, double instructionAmount){
        System.out.println(rank + "_____" + entityName+ "_____" + instructionAmount+"USD");
    }
    public void printOutgoingRanks(int rank, String entityName, double instructionAmount){
        System.out.println(rank + "_____" + entityName+ "_____" + instructionAmount+"USD");
    }
}
