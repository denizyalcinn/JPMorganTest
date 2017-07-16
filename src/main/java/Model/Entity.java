package Model;

/**
 * Created by denizyalcin on 16/07/2017.
 */
public class Entity {
    private String entityName;

    private double priceOfEntityUnit;

    private int unit;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public double getPriceOfEntityUnit() {
        return priceOfEntityUnit;
    }

    public void setPriceOfEntityUnit(double priceOfEntityUnit) {
        this.priceOfEntityUnit = priceOfEntityUnit;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
