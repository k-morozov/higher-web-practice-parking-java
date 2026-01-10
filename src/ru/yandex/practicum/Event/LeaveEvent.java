package ru.yandex.practicum.Event;

import ru.yandex.practicum.ParkingArea.ParkingArea;
import ru.yandex.practicum.Result.ErrorCode;
import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.Ticket;

public class LeaveEvent implements Event<Void> {
    private final int eventID;
    private final Ticket ticket;
    private boolean executed;

    public LeaveEvent(Ticket ticket) {
        this.eventID = ProviderEventID.get();
        this.ticket = ticket;
        this.executed = false;
    }
    @Override
    public Result<Void> execute(ParkingArea parkingArea) {
        if (this.executed) {
            return Result.failure(ErrorCode.EVENT_ALREADY_EXECUTED);
        }
        Result<Void> result = parkingArea.leave(this.ticket);
        this.executed = true;
        return result;
    }

    @Override
    public String toString() {
        return "LeaveEvent[eventID=" + eventID + ", ticket=" + ticket + ", executed=" + executed + "]";
    }
}