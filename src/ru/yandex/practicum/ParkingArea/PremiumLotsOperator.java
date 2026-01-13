package ru.yandex.practicum.ParkingArea;

import ru.yandex.practicum.ParkingLots.ParkingLots;
import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.LeavePolicy;
import ru.yandex.practicum.Ticket.PremiumLotTicket;
import ru.yandex.practicum.Ticket.Ticket;
import ru.yandex.practicum.Vehicle.ParkPolicy;
import ru.yandex.practicum.Vehicle.PremiumCar;

public class PremiumLotsOperator implements ParkPolicy<PremiumCar>, LeavePolicy<PremiumLotTicket> {
    private final ParkingOps ops;
    private final ParkingLots premiumLots;
    private final ParkingLots simpleLots;

    PremiumLotsOperator(ParkingOps ops, ParkingLots simpleLots, ParkingLots premiumLots) {
        this.ops = ops;
        this.simpleLots = simpleLots;
        this.premiumLots = premiumLots;
    }

    @Override
    public Result<Ticket> park(PremiumCar car) {
        if (this.premiumLots.hasFree()) {
            return ops.doPark(premiumLots, car);
        }

        return ops.doPark(simpleLots, car);
    }

    @Override
    public Result<Void> leave(PremiumLotTicket ticket) {
        return ops.doLeave(premiumLots, ticket);
    }
}
