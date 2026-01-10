package ru.yandex.practicum.Ticket;

import ru.yandex.practicum.ParkingArea.ManagerProvider;
import ru.yandex.practicum.Result.Result;

public class PremiumLotTicket implements Ticket {
    private final int ticketID;
    private final int pos;
    private final String number;

    public PremiumLotTicket(int pos, String number) {
        this.ticketID = ProviderTicketID.get();;
        this.pos = pos;
        this.number = number;
    }

    @Override
    public int getPos() {
        return pos;
    }

    @Override
    public String getVehicleNumber() {
        return number;
    }

    @Override
    public Result<Void> leave(ManagerProvider provider) {
        return provider.getPremiumLotsOperator().leave(this);
    }

    @Override
    public String toString() {
        return "PremiumLotTicket[ticket_id=" + ticketID + ", pos=" + pos + ", car number=" + number + "]";
    }
}
