package ru.yandex.practicum.ParkingArea;

import ru.yandex.practicum.ParkingLots.ParkingLots;
import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.Ticket;
import ru.yandex.practicum.Vehicle.Vehicle;

public interface ParkingOps {
    Result<Ticket> doPark(ParkingLots lots, Vehicle vehicle);
    Result<Void> doLeave(ParkingLots lots, Ticket ticket);
}
