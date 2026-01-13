package ru.yandex.practicum.Ticket;

import ru.yandex.practicum.ParkingArea.ManagerProvider;
import ru.yandex.practicum.ParkingException;
import ru.yandex.practicum.Result.Result;

public interface ApplierLeavePolicy {
    Result<Void> leave(ManagerProvider provider);
}
