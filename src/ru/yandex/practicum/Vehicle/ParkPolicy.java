package ru.yandex.practicum.Vehicle;

import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.Ticket;

public interface ParkPolicy<T extends Vehicle> {
    Result<Ticket> park(T car);
}
