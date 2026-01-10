package ru.yandex.practicum.Vehicle;

import ru.yandex.practicum.ParkingArea.ManagerProvider;
import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.Ticket;

public class ElectroCar implements Vehicle {

    private final String number;

    public ElectroCar(String number) {
        this.number = number;
    }

    @Override
    public String GetNumber() {
        return number;
    }

    @Override
    public Result<Ticket> park(ManagerProvider provider) {
        return provider.getElectroLotsOperator().park(this);
    }

    @Override
    public String toString() {
        return "ElectroCar[number=" + number + "]";
    }
}
