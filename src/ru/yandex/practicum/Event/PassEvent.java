package ru.yandex.practicum.Event;

import ru.yandex.practicum.ParkingArea.ParkingArea;
import ru.yandex.practicum.Result.Result;

public class PassEvent implements Event<Void> {
    private final int eventID;

    public PassEvent() {
        this.eventID = ProviderEventID.get();
    }

    @Override
    public Result<Void> execute(ParkingArea parkingArea) {
        return Result.success();
    }

    @Override
    public String toString() {
        return "PassEvent[eventID=" + eventID + "]";
    }
}