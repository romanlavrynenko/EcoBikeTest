package entities;

import java.util.Comparator;
import java.util.Objects;

public abstract class EcoBikeEntity {
    private String brand;
    private Integer weight;
    private Boolean isAvailableForLights;
    private String color;
    private Integer price;

    public EcoBikeEntity() {
    }

    public EcoBikeEntity(String brand, Integer weight, Boolean isAvailableForLights, String color, Integer price) {
        this.brand = brand;
        this.weight = weight;
        this.isAvailableForLights = isAvailableForLights;
        this.color = color;
        this.price = price;
    }


    public static Comparator<EcoBikeEntity> compareByBrand = new Comparator<EcoBikeEntity>() {

        @Override
        public int compare(EcoBikeEntity o1, EcoBikeEntity o2) {
            String brandName1 = o1.getBrand();
            String brandName2 = o2.getBrand();
            return brandName1.compareTo(brandName2);

        }
    };

    /**
     * Compares all fields of Entities
     *
     * @param o its Object EcoBikeEntity which compare with another EcoBikeEntity
     * @return true if it's equals and false if it's not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EcoBikeEntity that = (EcoBikeEntity) o;
        if (brand == null) {

            return weight == that.weight &&
                    isAvailableForLights == that.isAvailableForLights &&
                    price == that.price &&
                    null == that.brand &&
                    color.equals(that.color);
        } else if (color == null) {

            return weight == that.weight &&
                    isAvailableForLights == that.isAvailableForLights &&
                    price == that.price &&
                    brand.equals(that.brand) &&
                    null == that.color;
        }

        return weight == that.weight &&
                isAvailableForLights == that.isAvailableForLights &&
                price == that.price &&
                brand.equals(that.brand) &&
                color.equals(that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, weight, isAvailableForLights, color, price);
    }

    public String getBrand() {
        return brand;
    }

    public Integer getWeight() {
        return weight;
    }


    public Boolean isAvailableForLights() {
        return isAvailableForLights;
    }

    public String getColor() {
        return color;
    }

    public Integer getPrice() {
        return price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setAvailableForLights(Boolean availableForLights) {
        isAvailableForLights = availableForLights;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
