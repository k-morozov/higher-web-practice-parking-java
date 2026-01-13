package ru.yandex.practicum.ParkingArea;

import ru.yandex.practicum.ParkingLots.ParkingLots;
import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.ElectroLotTicket;
import ru.yandex.practicum.Ticket.LeavePolicy;
import ru.yandex.practicum.Ticket.Ticket;
import ru.yandex.practicum.Vehicle.ElectroCar;
import ru.yandex.practicum.Vehicle.ParkPolicy;

public class ElectroLotsOperator implements ParkPolicy<ElectroCar>, LeavePolicy<ElectroLotTicket> {
    private final ParkingOps ops;
    private final ParkingLots electroLots;

    ElectroLotsOperator(ParkingOps ops, ParkingLots electroLots) {
        this.ops = ops;
        this.electroLots = electroLots;
    }

    @Override
    public Result<Ticket> park(ElectroCar car) {
        return ops.doPark(electroLots, car);
    }

    @Override
    public Result<Void> leave(ElectroLotTicket ticket) {
        return ops.doLeave(electroLots, ticket);
    }
}
