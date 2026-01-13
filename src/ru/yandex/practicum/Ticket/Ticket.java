package ru.yandex.practicum.Ticket;

public interface Ticket extends ApplierLeavePolicy {
    int getPos();
    String getVehicleNumber();
}
