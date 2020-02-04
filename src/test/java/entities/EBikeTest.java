package entities;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class EBikeTest {

    private EBike eBike = new EBike();


    @Test
    void getType() {
        assertEquals(eBike.getType(), "E-BIKE", "Type should be the same");
    }

    @Test
    void getMaxSpeed() {
        eBike.setMaxSpeed(20);
        assertEquals(20, eBike.getMaxSpeed(), "Speed should be the same");
    }

    @Test
    void getBatteryCapacity() {
        eBike.setBatteryCapacity(12000);
        assertEquals(12000, eBike.getBatteryCapacity(), "Battery capacity should be the same");
        eBike.setBatteryCapacity(null);
        assertNull(eBike.getBatteryCapacity(), "Battery capacity should be null");
    }

    @Test
    void setMaxSpeed() {
        assertNull(eBike.getMaxSpeed());
        eBike.setMaxSpeed(20);
        assertNotNull(eBike.getMaxSpeed());
    }

    @Test
    void setBatteryCapacity() {
        assertNull(eBike.getBatteryCapacity());
        eBike.setBatteryCapacity(15000);
        assertNotNull(eBike.getBatteryCapacity());
    }

    @Test
    void testEquals() {
        EBike eBike1 = new EBike();
        eBike.setColor(new StringBuffer().append("qwe").toString());
        eBike1.setColor("qwe");
        assertEquals(eBike, eBike1);
        eBike1.setColor("qwe");
        assertEquals(eBike1,eBike);
    }
}