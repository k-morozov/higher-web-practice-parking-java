package ru.yandex.practicum.ParkingArea;

import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.Ticket;
import ru.yandex.practicum.Vehicle.Vehicle;

public class ParkingArea {
    private final ManagerProvider impl;

    public ParkingArea(int countSimpleLots, int countElectricLots, int countPremiumLots) {
        if (countSimpleLots < 0) {
            throw new IllegalArgumentException("countSimpleLots can't be negative");
        }
        if (countElectricLots < 0) {
            throw new IllegalArgumentException("countElectricLots can't be negative");
        }
        if (countPremiumLots < 0) {
            throw new IllegalArgumentException("countPremiumLots can't be negative");
        }
        this.impl = new ParkingManager(countSimpleLots, countElectricLots, countPremiumLots);
    }

    public Result<Ticket> park(Vehicle vehicle) {
        return vehicle.park(impl);
    }

    public Result<Void> leave(Ticket ticket) {
        return ticket.leave(impl);
    }
}
