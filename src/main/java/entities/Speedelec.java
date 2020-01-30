package entities;

import java.util.Objects;

public class Speedelec extends EcoBikeEntity {
    private String type;
    private Integer maxSpeed;
    private Integer batteryCapacity;


    public Speedelec(String brand, Integer maxSpeed, Integer weight, Boolean isAvailableForLights,
                     Integer batteryCapacity, String color, Integer price) {
        super(brand, weight, isAvailableForLights, color, price);
        this.type = "SPEEDELEC";
        this.maxSpeed = maxSpeed;
        this.batteryCapacity = batteryCapacity;

    }

    public Speedelec() {
        this.type = "SPEEDELEC";
    }

    public String getType() {
        return type;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Speedelec speedelec = (Speedelec) o;
        return super.equals(o) &&
                maxSpeed == speedelec.maxSpeed &&
                batteryCapacity == speedelec.batteryCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxSpeed, batteryCapacity);

    }

    @Override
    public String toString() {
        return "SPEEDELEC " + getBrand() + "; " +
                maxSpeed + "; " +
                getWeight() + "; " +
                isAvailableForLights() + "; " +
                batteryCapacity + "; " +
                getColor() + "; " +
                getPrice();
    }
}
