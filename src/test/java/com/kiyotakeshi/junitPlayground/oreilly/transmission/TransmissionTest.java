package com.kiyotakeshi.junitPlayground.oreilly.transmission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TransmissionTest {
    private Transmission transmission;
    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        transmission = new Transmission(car);
    }

    @Test
    void remainsInDriveAfterAcceleration() {
        // exercise(act)
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(35);

        // verify(assert)
        assertThat(transmission.getGear()).isEqualTo(Gear.DRIVE);
    }

    @Test
    @DisplayName("走行中はPへのシフトをは行わない")
    void ignoresShiftToParkWhileInDrive() {
        // set up(arrange)
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(25);

        // exercise(act)
        transmission.shift(Gear.PARK);

        // verify(assert)
        assertThat(transmission.getGear()).isEqualTo(Gear.DRIVE);
    }

    @Test
    void allowsShiftToParkWhenNotMoving() {
        // set up(arrange)
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(30);
        car.brakeToStop();

        // exercise(act)
        transmission.shift(Gear.PARK);

        // verify(assert)
        assertThat(transmission.getGear()).isEqualTo(Gear.PARK);
    }
}
