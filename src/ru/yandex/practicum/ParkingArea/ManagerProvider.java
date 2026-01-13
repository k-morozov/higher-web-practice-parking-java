package ru.yandex.practicum.ParkingArea;

public interface ManagerProvider {
    SimpleLotsOperator getSimpleLotsOperator();
    ElectroLotsOperator getElectroLotsOperator();
    PremiumLotsOperator getPremiumLotsOperator();
}
