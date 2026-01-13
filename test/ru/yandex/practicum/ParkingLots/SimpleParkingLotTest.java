package ru.yandex.practicum.ParkingLots;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.Result.ErrorCode;
import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Vehicle.ElectroCar;
import ru.yandex.practicum.Vehicle.PremiumCar;
import ru.yandex.practicum.Vehicle.SimpleCar;
import ru.yandex.practicum.Vehicle.Vehicle;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SimpleParkingLotTest {
    @Test
    void FailedParkUnsupportedElectroCar() {
        ParkingLot lot = new SimpleParkingLot();
        Vehicle electroCar = new ElectroCar("aaa111");

        assertEquals(ErrorCode.LOT_NOT_SUPPORTED_VEHICLE, lot.enter(electroCar).getErrorCode());

        assertTrue(lot.isFree());
    }

    @Test
    void FailedLeaveIsFreeLot() {
        ParkingLot lot = new SimpleParkingLot();

        Result<Void> result = lot.leave();
        assertEquals(ErrorCode.LOT_IS_FREE, result.getErrorCode());

        assertTrue(lot.isFree());
    }

    @Test
    void FailedParkTwice() {
        ParkingLot lot = new SimpleParkingLot();
        Vehicle simpleCar = new SimpleCar("aaa111");

        lot.enter(simpleCar);
        assertFalse(lot.isFree());

        assertEquals(ErrorCode.LOT_NOT_FREE, lot.enter(simpleCar).getErrorCode());

        assertFalse(lot.isFree());

        Result<Void> result = lot.leave();
        assertTrue(result.isSuccess());
        assertTrue(lot.isFree());
    }

    @Test
    void FailedLeaveTwice() {
        ParkingLot lot = new SimpleParkingLot();
        Vehicle simpleCar = new SimpleCar("aaa111");

        lot.enter(simpleCar);
        assertFalse(lot.isFree());

        Result<Void> result = lot.leave();
        assertTrue(result.isSuccess());

        assertTrue(lot.isFree());

        result = lot.leave();
        assertEquals(ErrorCode.LOT_IS_FREE, result.getErrorCode());

        assertTrue(lot.isFree());
    }

    @Test
    void ParkSomeCars() {
        ParkingLot lot = new SimpleParkingLot();
        ArrayList<Vehicle> cars = new ArrayList<>();
        cars.add(new SimpleCar("aaa111"));
        cars.add(new SimpleCar("aaa111"));
        cars.add(new SimpleCar("aaa111"));
        cars.add(new PremiumCar("aaa111"));
        cars.add(new PremiumCar("aaa111"));

        for (Vehicle car : cars) {
            lot.enter(car);
            assertFalse(lot.isFree());

            Result<Void> result = lot.leave();
            assertTrue(result.isSuccess());

            assertTrue(lot.isFree());
        }
    }
}