package ru.yandex.practicum.ParkingArea;

import ru.yandex.practicum.ParkingLots.ParkingLots;
import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.LeavePolicy;
import ru.yandex.practicum.Ticket.SimpleLotTicket;
import ru.yandex.practicum.Ticket.Ticket;
import ru.yandex.practicum.Vehicle.ParkPolicy;
import ru.yandex.practicum.Vehicle.SimpleCar;

public class SimpleLotsOperator implements ParkPolicy<SimpleCar>, LeavePolicy<SimpleLotTicket> {
    private final ParkingOps ops;
    private final ParkingLots simpleLots;

    SimpleLotsOperator(ParkingOps ops, ParkingLots simpleLots) {
        this.ops = ops;
        this.simpleLots = simpleLots;
    }

    @Override
    public Result<Ticket> park(SimpleCar car) {
        return ops.doPark(simpleLots, car);
    }

    @Override
    public Result<Void> leave(SimpleLotTicket ticket) {
        return ops.doLeave(simpleLots, ticket);
    }
}
