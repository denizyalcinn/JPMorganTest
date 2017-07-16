import Controller.DailyTradeController;
import Model.Entity;
import Model.Instruction;
import View.DailyTradeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by denizyalcin on 16/07/2017.
 */
public class DailyTradeReportingDemo {
    public static void main(String[] args){

        DailyTradeController controller = null;

        //Create a view : to write instruction details on console
        DailyTradeView view = new DailyTradeView();

        //fetch  instructions
       List<Instruction> instructions = retrieveAllInstructions();


        controller = new DailyTradeController(instructions,view);

        controller.updateDailyTradeView();

    }

    private static List<Instruction> retrieveAllInstructions(){
        List<Instruction> instructions = new ArrayList<>();

     //   instructions.add(retrieveInstructions1());
        instructions.add(retrieveInstructions2());

        instructions.add(retrieveInstructions3());

        instructions.sort(Comparator.comparing(Instruction::getSettlementDate));

        return instructions;
    }

    private static Instruction retrieveInstructions1() {

        Entity entity = new Entity();
        Instruction instruction = new Instruction();

        entity.setEntityName("foo");
        instruction.setBuySellFlag("Buy");
        instruction.setAgreedFx(0.50);
        instruction.setCurrency("SGP");

        SimpleDateFormat formatDate=new SimpleDateFormat("dd/MM/yyyy");
        String instructionDate="01/01/2016";
        String settlementDate="02/01/2016";

        try {
            instruction.setInstructionDate(formatDate.parse(instructionDate));
            instruction.setSettlementDate(formatDate.parse(settlementDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        entity.setUnit(200);
        entity.setPriceOfEntityUnit(100.25);

        instruction.setEntity(entity);
        return instruction;
    }

    private static Instruction retrieveInstructions2() {

        Entity entity = new Entity();
        Instruction instruction = new Instruction();

        entity.setEntityName("bar");
        instruction.setBuySellFlag("Buy");
        instruction.setAgreedFx(0.22);
        instruction.setCurrency("AED");

        SimpleDateFormat formatDate=new SimpleDateFormat("dd/MM/yyyy");
        String instructionDate="05/01/2016";
        String settlementDate="08/01/2016";

        try {
            instruction.setInstructionDate(formatDate.parse(instructionDate));
            instruction.setSettlementDate(formatDate.parse(settlementDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        entity.setUnit(450);
        entity.setPriceOfEntityUnit(150.5);

        instruction.setEntity(entity);
        return instruction;
    }

    private static Instruction retrieveInstructions3() {

        Entity entity = new Entity();
        Instruction instruction = new Instruction();

        entity.setEntityName("deniz");
        instruction.setBuySellFlag("Sell");
        instruction.setAgreedFx(0.10);
        instruction.setCurrency("TRY");

        SimpleDateFormat formatDate=new SimpleDateFormat("dd/MM/yyyy");
        String instructionDate="01/01/2016";
        String settlementDate="08/01/2016";
        try {
            instruction.setInstructionDate(formatDate.parse(instructionDate));
            instruction.setSettlementDate(formatDate.parse(settlementDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        entity.setUnit(100);
        entity.setPriceOfEntityUnit(100);

        instruction.setEntity(entity);
        return instruction;
    }


}
