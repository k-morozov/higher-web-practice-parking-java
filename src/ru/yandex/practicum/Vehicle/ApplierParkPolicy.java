package ru.yandex.practicum.Vehicle;

import ru.yandex.practicum.ParkingArea.ManagerProvider;
import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.Ticket;
import ru.yandex.practicum.ParkingException;

public interface ApplierParkPolicy {
    Result<Ticket> park(ManagerProvider manager);
}
