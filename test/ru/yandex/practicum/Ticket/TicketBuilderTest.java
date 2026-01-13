package ru.yandex.practicum.Ticket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketBuilderTest {
    private TicketBuilder<SimpleLotTicket> builder;

    @BeforeEach
    void setUp() {
        builder = new TicketBuilder<>(SimpleLotTicket::new);
    }

    @Test
    void addPosTwice() {
        assertDoesNotThrow(() -> builder.addPos(1));
        assertThrows(IllegalArgumentException.class, () -> {
            builder.addPos(2);
        });
    }

    @Test
    void addNumberTwice() {
        assertDoesNotThrow(() -> builder.addNumber("sss"));
        assertThrows(IllegalArgumentException.class, () -> {
            builder.addNumber("sss");
        });
    }

    @Test
    void build() {
        Ticket ticket = assertDoesNotThrow(() -> builder.addPos(1).addNumber("aaa").build());
        assertEquals(1, ticket.getPos());
        assertEquals("aaa", ticket.getVehicleNumber());
    }
}