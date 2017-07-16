package Controller;

import Model.Instruction;
import View.DailyTradeView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by denizyalcin on 16/07/2017.
 */
public class DailyTradeController {
    private List<Instruction> instructionModel;
    private DailyTradeView  dailyTradeView;
    private WeekDaysController weekDays;
    private int incomingHead=0;
    private int outgoingHead=0;
    private int incomingRank =1;
    private int outgoingRank =1;

    Set<Date> settlementDates= new HashSet<Date>(64);
    //Set<Instruction> incomingRank = new HashSet<Instruction>(64);
    //Set<Instruction> outgoingRank = new HashSet<Instruction>(64);

    public DailyTradeController (List<Instruction> instructionModel, DailyTradeView dailyTradeView){
        this.instructionModel = instructionModel;
        this.dailyTradeView = dailyTradeView;
    }

    private void decideBuySellFlag (){

        for (Instruction i: instructionModel){
            String givenFlag = i.getBuySellFlag();
            if(givenFlag!= null){
                if(givenFlag.equals("BUY") || givenFlag.equals("buy") || givenFlag.equals("Buy") || givenFlag.equals("B") ){
                    i.setBuySellFlag("B");
                }else if (givenFlag.equals("SELL") || givenFlag.equals("sell") || givenFlag.equals("Sell") || givenFlag.equals("S")) {
                    i.setBuySellFlag("S");
                }else {
                    i.setBuySellFlag("Unknown Action");
                }
            }else{
                    throw new NullPointerException("Null pointer exception.");
                }
        }
    }

    private void giveSettlementDate(){
        weekDays = new WeekDaysController();

        for (Instruction i: instructionModel){

            int settlementFlag= weekDays.isWorkingDate(i.getSettlementDate(),i.getCurrency());
            Date newDate = new Date();

            if(settlementFlag == 0){
                //sameday
                newDate = i.getSettlementDate();
            }else if (settlementFlag == 1){
                //the other day
                Calendar c = Calendar.getInstance();
                c.setTime(i.getSettlementDate());
                c.add(Calendar.DATE, 1);
                newDate= c.getTime();
            }else if(settlementFlag == 2) {
                //the other day
                Calendar c = Calendar.getInstance();
                c.setTime(i.getSettlementDate());
                c.add(Calendar.DATE, 2);
                newDate= c.getTime();
            }

            i.setSettlementDate(newDate);

            //add them to a list
            settlementDates.add(i.getSettlementDate());
        }

    }

    private double calculateAmountofTrade(double agreedFx, double priceOfEntityUnit, int unit){
        double amountInUSD =0D;

        amountInUSD = agreedFx * priceOfEntityUnit * unit;

        return amountInUSD;
    }
    private double calculateOutgoingAmount(Date tradeDate){
        double totalOutgoingAmount =0D;

        for (Instruction i: instructionModel){
            double newAmount = calculateAmountofTrade(i.getAgreedFx(),i.getEntity().getPriceOfEntityUnit(),i.getEntity().getUnit());
            i.setAmount(newAmount);
            if ((i.getBuySellFlag().equals("B")) && (i.getSettlementDate().equals(tradeDate))){
                totalOutgoingAmount = totalOutgoingAmount + newAmount;
            }
        }
        return totalOutgoingAmount;
    }

    private double calculateIncomingAmount(Date tradeDate){
        double totalIncomingAmount =0D;
        for (Instruction i: instructionModel){
            double newAmount = calculateAmountofTrade(i.getAgreedFx(),i.getEntity().getPriceOfEntityUnit(),i.getEntity().getUnit());
            i.setAmount(newAmount);
            if ( (i.getBuySellFlag() == "S")  && (i.getSettlementDate().equals(tradeDate))){
                totalIncomingAmount = totalIncomingAmount + newAmount;
            }
        }
        return totalIncomingAmount;
    }

    public void updateDailyTradeView(){
        giveSettlementDate();
        decideBuySellFlag();

        for (Date i: settlementDates){
            Date tradeDate = i;

            DateFormat df = new SimpleDateFormat("dd.mm.yyyyy ");
            String reportDate = df.format(i);
            dailyTradeView.printInstructionDetails( reportDate,
                                                    calculateIncomingAmount(tradeDate),
                                                    calculateOutgoingAmount(tradeDate) );
        }
        instructionModel.sort(Comparator.comparing(Instruction::getAmount));

        for (Instruction i: instructionModel){
            if(i.getBuySellFlag() == "S"){
                if (incomingHead==0){
                    dailyTradeView.printIncomingHeadlines();
                }
                dailyTradeView.printIncomingRanks(incomingRank,i.getEntity().getEntityName(),i.getAmount());
                incomingRank ++;
                incomingHead++;
            }else if(i.getBuySellFlag() == "B"){
                if (outgoingHead==0){
                    dailyTradeView.printOutgoingHeadlines();
                }
                dailyTradeView.printOutgoingRanks(outgoingRank,i.getEntity().getEntityName(),i.getAmount());
                outgoingRank ++;
                outgoingHead++;
            }
        }


    }
}
