package ru.yandex.practicum.Event;

public class ProviderEventID {
    private static int id = 117;

    public static int get() {
        return id++;
    }
}
