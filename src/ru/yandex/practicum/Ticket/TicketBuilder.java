package ru.yandex.practicum.Ticket;

import ru.yandex.practicum.ParkingException;

import java.util.Optional;
import java.util.function.BiFunction;

public class TicketBuilder<T extends Ticket> {
    private final BiFunction<Integer, String, T> factory;
    private Optional<Integer> pos;
    private Optional<String> number;

    public TicketBuilder(BiFunction<Integer, String, T> factory) {
        this.factory = factory;
        this.pos = Optional.empty();
        this.number = Optional.empty();
    }

    public TicketBuilder<T> addPos(int pos) throws IllegalArgumentException {
        if (this.pos.isPresent()) {
            throw new IllegalArgumentException("pos was setted");
        }

        this.pos = Optional.of(pos);
        return this;
    }

    public TicketBuilder<T> addNumber(String number) throws IllegalArgumentException {
        if (this.number.isPresent()) {
            throw new IllegalArgumentException("pos was setted");
        }

        this.number = Optional.of(number);
        return this;
    }

    public Ticket build() throws IllegalArgumentException {
        if (pos.isEmpty()) {
            throw new IllegalArgumentException("pos is empty");
        }
        if (number.isEmpty()) {
            throw new IllegalArgumentException("number is empty");
        }
        return factory.apply(pos.get(), number.get());
    }
}
