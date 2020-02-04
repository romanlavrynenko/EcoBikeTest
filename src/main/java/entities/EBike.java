package entities;

import java.util.Objects;

public class EBike extends EcoBikeEntity {
    private String type;
    private Integer maxSpeed;
    private Integer batteryCapacity;

    public EBike() {
        this.type = "E-BIKE";
    }

    public EBike(String brand, Integer maxSpeed, Integer weight, Boolean isAvailableForLights,
                     Integer batteryCapacity, String color, Integer price) {
        super(brand, weight, isAvailableForLights, color, price);
        this.type = "E-BIKE";
        this.maxSpeed = maxSpeed;
        this.batteryCapacity = batteryCapacity;

    }

    public String getType() {
        return type;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EBike eBike = (EBike) o;
        return super.equals(o) &&
                maxSpeed == eBike.maxSpeed &&
                batteryCapacity == eBike.batteryCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxSpeed, batteryCapacity);

    }

    @Override
    public String toString() {
        return "E-BIKE " + getBrand() + "; " +
                maxSpeed + "; " +
                getWeight() + "; " +
                isAvailableForLights() + "; " +
                batteryCapacity + "; " +
                getColor() + "; " +
                getPrice();
    }
}
