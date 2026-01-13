package ru.yandex.practicum.Event;

import ru.yandex.practicum.ParkingArea.ParkingArea;
import ru.yandex.practicum.Result.ErrorCode;
import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.Ticket;
import ru.yandex.practicum.Vehicle.Vehicle;

public class ParkEvent implements Event<Ticket> {
    private final int eventID;
    private final Vehicle vehicle;
    private boolean executed;

    public ParkEvent(Vehicle vehicle) {
        this.eventID = ProviderEventID.get();
        this.vehicle = vehicle;
        this.executed = false;
    }
    @Override
    public Result<Ticket> execute(ParkingArea parkingArea) {
        if (this.executed) {
            return Result.failure(ErrorCode.EVENT_ALREADY_EXECUTED);
        }
        Result<Ticket> result = parkingArea.park(this.vehicle);
        this.executed = true;
        return result;
    }

    @Override
    public String toString() {
        return "ParkEvent[eventID=" + eventID + ", vehicle=" + vehicle +", executed=" + executed + "]";
    }
}
