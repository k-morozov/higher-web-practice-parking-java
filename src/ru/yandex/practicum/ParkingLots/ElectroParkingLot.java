package ru.yandex.practicum.ParkingLots;

import ru.yandex.practicum.Result.ErrorCode;
import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.ElectroLotTicket;
import ru.yandex.practicum.Ticket.Ticket;
import ru.yandex.practicum.Ticket.TicketBuilder;
import ru.yandex.practicum.Vehicle.ElectroCar;
import ru.yandex.practicum.Vehicle.Vehicle;

import java.util.Optional;

public class ElectroParkingLot implements ParkingLot {

    private Optional<Vehicle> vehicle;

    public ElectroParkingLot() {
        this.vehicle = Optional.empty();
    }

    @Override
    public Result<TicketBuilder<? extends Ticket>> enter(Vehicle vehicle) {
        if (!(vehicle instanceof ElectroCar)) {
            return Result.failure(ErrorCode.LOT_NOT_SUPPORTED_VEHICLE);
        }

        if (!isFree()) {
            return Result.failure(ErrorCode.LOT_NOT_FREE);
        }

        this.vehicle = Optional.of(vehicle);

        return Result.success(new TicketBuilder<>(ElectroLotTicket::new));
    }

    @Override
    public boolean isFree() {
        return vehicle.isEmpty();
    }

    @Override
    public Result<Void> leave() {
        if (isFree()) {
            return Result.failure(ErrorCode.LOT_IS_FREE);
        }

        this.vehicle = Optional.empty();
        return Result.success();
    }
}
