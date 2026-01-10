package ru.yandex.practicum.ParkingLots;

import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.Ticket;
import ru.yandex.practicum.Ticket.TicketBuilder;
import ru.yandex.practicum.Vehicle.Vehicle;

public interface ParkingLot {

    Result<TicketBuilder<? extends Ticket>> enter(Vehicle vehicle);

    boolean isFree();

    Result<Void> leave();
}
