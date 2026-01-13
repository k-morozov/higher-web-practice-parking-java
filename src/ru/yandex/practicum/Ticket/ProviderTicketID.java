package ru.yandex.practicum.Ticket;

public class ProviderTicketID {
    private static int id = 44;

    public static int get() {
        return id++;
    }
}
