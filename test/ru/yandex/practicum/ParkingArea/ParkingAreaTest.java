package ru.yandex.practicum.ParkingArea;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.Result.ErrorCode;
import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.ElectroLotTicket;
import ru.yandex.practicum.Ticket.PremiumLotTicket;
import ru.yandex.practicum.Ticket.SimpleLotTicket;
import ru.yandex.practicum.Ticket.Ticket;
import ru.yandex.practicum.Vehicle.ElectroCar;
import ru.yandex.practicum.Vehicle.PremiumCar;
import ru.yandex.practicum.Vehicle.SimpleCar;
import ru.yandex.practicum.Vehicle.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

class ParkingAreaTest {
    @Test
    void ParkOneSimpleCarTwice() {
        ParkingArea parking = new ParkingArea(1, 0, 0);

        Vehicle car1 = new SimpleCar("aaa111");
        Result<Ticket> result1 = assertDoesNotThrow(() -> parking.park(car1));
        assertTrue(result1.isSuccess());

        Ticket ticket1 = result1.getValue().get();
        assertNotNull(ticket1);

        assertEquals(0, ticket1.getPos());
        assertEquals(car1.GetNumber(), ticket1.getVehicleNumber());
        assertDoesNotThrow(() -> parking.leave(ticket1));

        Result<Ticket> result2 = assertDoesNotThrow(() -> parking.park(car1));
        Ticket ticket2 = result2.getValue().get();
        assertNotNull(ticket2);
        assertEquals(0, ticket2.getPos());
        assertEquals(car1.GetNumber(), ticket2.getVehicleNumber());
    }

    @Test
    void ParkTwoSimpleCars() {
        ParkingArea parking = new ParkingArea(1, 0, 0);

        Vehicle car1 = new SimpleCar("aaa111");
        Result<Ticket> result1 = assertDoesNotThrow(() -> parking.park(car1));
        Ticket ticket1 = result1.getValue().get();
        assertNotNull(ticket1);
        assertEquals(0, ticket1.getPos());
        assertEquals(car1.GetNumber(), ticket1.getVehicleNumber());

        Vehicle car2 = new SimpleCar("aaa112");
        Result<Ticket> result2 = assertDoesNotThrow(() -> parking.park(car2));
        assertTrue( result2.getValue().isEmpty());

        assertDoesNotThrow(() -> parking.leave(ticket1));

        result2 = assertDoesNotThrow(() -> parking.park(car2));
        Ticket ticket2 = result2.getValue().get();
        assertNotNull(ticket2);
        assertEquals(0, ticket2.getPos());
        assertEquals(car2.GetNumber(), ticket2.getVehicleNumber());
    }

    @Test
    void CheckLimitSimpleCars() {
        ParkingArea parking = new ParkingArea(2, 0, 0);

        Vehicle car1 = new SimpleCar("a1");
        Result<Ticket> result1 = assertDoesNotThrow(() -> parking.park(car1));
        Ticket ticket1 = result1.getValue().get();
        assertNotNull(ticket1);
        assertTrue(ticket1 instanceof SimpleLotTicket);
        assertEquals(0, ticket1.getPos());
        assertEquals(car1.GetNumber(), ticket1.getVehicleNumber());

        Vehicle car2 = new SimpleCar("a2");
        Result<Ticket> result2 = assertDoesNotThrow(() -> parking.park(car2));
        Ticket ticket2 = result2.getValue().get();
        assertNotNull(ticket2);
        assertTrue(ticket2 instanceof SimpleLotTicket);
        assertEquals(1, ticket2.getPos());
        assertEquals(car2.GetNumber(), ticket2.getVehicleNumber());

        {
            Vehicle unparked_car = new SimpleCar("a3");

            Result<Ticket> result3 = assertDoesNotThrow(() -> parking.park(unparked_car));
            assertEquals(ErrorCode.NOT_FOUND_EMPTY_LOT, result3.getErrorCode());
        }

        assertDoesNotThrow(() -> parking.park(car2));

        {
            Vehicle unparked_car = new ElectroCar("a3");

            Result<Ticket> result3 = assertDoesNotThrow(() -> parking.park(unparked_car));
            assertEquals(ErrorCode.NOT_FOUND_EMPTY_LOT, result3.getErrorCode());
        }

        {
            Vehicle unparked_car = new PremiumCar("a3");

            Result<Ticket> result3 = assertDoesNotThrow(() -> parking.park(unparked_car));
            assertEquals(ErrorCode.NOT_FOUND_EMPTY_LOT, result3.getErrorCode());
        }
    }

    @Test
    void CheckLimitElectroCars() {
        ParkingArea parking = new ParkingArea(0, 2, 0);

        Vehicle car1 = new ElectroCar("a1");
        Result<Ticket> result1 = assertDoesNotThrow(() -> parking.park(car1));
        Ticket ticket1 = result1.getValue().get();
        assertNotNull(ticket1);
        assertTrue(ticket1 instanceof ElectroLotTicket);
        assertEquals(0, ticket1.getPos());
        assertEquals(car1.GetNumber(), ticket1.getVehicleNumber());

        Vehicle car2 = new ElectroCar("a2");
        Result<Ticket> result2 = assertDoesNotThrow(() -> parking.park(car2));
        Ticket ticket2 = result2.getValue().get();
        assertNotNull(ticket2);
        assertTrue(ticket2 instanceof ElectroLotTicket);
        assertEquals(1, ticket2.getPos());
        assertEquals(car2.GetNumber(), ticket2.getVehicleNumber());

        {
            Vehicle unparked_car = new ElectroCar("a3");

            Result<Ticket> result3 = assertDoesNotThrow(() -> parking.park(unparked_car));
            assertEquals(ErrorCode.NOT_FOUND_EMPTY_LOT, result3.getErrorCode());
        }

        assertDoesNotThrow(() -> parking.park(car2));

        {
            Vehicle unparked_car = new SimpleCar("a3");

            Result<Ticket> result3 = assertDoesNotThrow(() -> parking.park(unparked_car));
            assertEquals(ErrorCode.NOT_FOUND_EMPTY_LOT, result3.getErrorCode());
        }

        {
            Vehicle unparked_car = new PremiumCar("a3");

            Result<Ticket> result3 = assertDoesNotThrow(() -> parking.park(unparked_car));
            assertEquals(ErrorCode.NOT_FOUND_EMPTY_LOT, result3.getErrorCode());
        }
    }

    @Test
    void CheckLimitPremiumCars() {
        ParkingArea parking = new ParkingArea(1, 0, 2);

        Vehicle car1 = new PremiumCar("a1");
        Result<Ticket> result1 = assertDoesNotThrow(() -> parking.park(car1));
        Ticket ticket1 = result1.getValue().get();
        assertNotNull(ticket1);
        assertEquals(0, ticket1.getPos());
        assertTrue(ticket1 instanceof PremiumLotTicket);
        assertEquals(car1.GetNumber(), ticket1.getVehicleNumber());

        Vehicle car2 = new PremiumCar("a2");
        Result<Ticket> result2 = assertDoesNotThrow(() -> parking.park(car2));
        Ticket ticket2 = result2.getValue().get();
        assertNotNull(ticket2);
        assertTrue(ticket2 instanceof PremiumLotTicket);
        assertEquals(1, ticket2.getPos());
        assertEquals(car2.GetNumber(), ticket2.getVehicleNumber());

        {
            Vehicle parked_car = new PremiumCar("a3");

            Result<Ticket> result3 = assertDoesNotThrow(() -> parking.park(parked_car));
            Ticket ticket3 = result3.getValue().get();
            assertNotNull(ticket3);
            assertTrue(ticket3 instanceof SimpleLotTicket);
            assertEquals(0, ticket3.getPos());
            assertEquals(parked_car.GetNumber(), ticket3.getVehicleNumber());
        }

        assertDoesNotThrow(() -> parking.park(car2));

        {
            Vehicle unparked_car = new SimpleCar("a3");

            Result<Ticket> result3 = assertDoesNotThrow(() -> parking.park(unparked_car));
            assertEquals(ErrorCode.NOT_FOUND_EMPTY_LOT, result3.getErrorCode());
        }

        {
            Vehicle unparked_car = new ElectroCar("a3");

            Result<Ticket> result3 = assertDoesNotThrow(() -> parking.park(unparked_car));
            assertEquals(ErrorCode.NOT_FOUND_EMPTY_LOT, result3.getErrorCode());
        }
    }
}