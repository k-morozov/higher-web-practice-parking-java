package ru.yandex.practicum.Ticket;

import ru.yandex.practicum.ParkingException;
import ru.yandex.practicum.Result.Result;

public interface LeavePolicy<T extends Ticket> {
    Result<Void> leave(T ticket) throws ParkingException;
}
