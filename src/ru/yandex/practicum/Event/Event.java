package ru.yandex.practicum.Event;

import ru.yandex.practicum.ParkingArea.ParkingArea;
import ru.yandex.practicum.Result.Result;

public interface Event<T> {
    Result<T> execute(ParkingArea parkingArea);
}
