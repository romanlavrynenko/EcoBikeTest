package entities;

import java.util.Objects;

public class FoldingBike  extends EcoBikeEntity{
    private String type;
    private Integer sizeOfWheels;
    private Integer numberOfGears;

    public FoldingBike() {
        this.type = "FOLDING BIKE";
    }

    public FoldingBike(String brand, Integer sizeOfWheels, Integer numberOfGears, Integer weight,
                       Boolean isAvailableForLights, String color, Integer price) {
        super(brand,weight,isAvailableForLights,color,price);
        this.type = "FOLDING BIKE";
        this.sizeOfWheels = sizeOfWheels;
        this.numberOfGears = numberOfGears;

    }

    public String getType() {
        return type;
    }

    public Integer getSizeOfWheels() {
        return sizeOfWheels;
    }

    public void setSizeOfWheels(Integer sizeOfWheels) {
        this.sizeOfWheels = sizeOfWheels;
    }

    public Integer getNumberOfGears() {
        return numberOfGears;
    }

    public void setNumberOfGears(Integer numberOfGears) {
        this.numberOfGears = numberOfGears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FoldingBike foldingBike = (FoldingBike) o;
        return super.equals(o) &&
                sizeOfWheels == foldingBike.sizeOfWheels &&
                numberOfGears == foldingBike.numberOfGears;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sizeOfWheels, numberOfGears);
    }

    @Override
    public String toString() {
        return "FOLDING BIKE " + getBrand() + "; " +
                sizeOfWheels + "; " +
                numberOfGears + "; " +
                getWeight() + "; " +
                isAvailableForLights() + "; " +
                getColor() + "; " +
                getPrice();
    }
}

