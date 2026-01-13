package ru.yandex.practicum;

import ru.yandex.practicum.Event.Event;
import ru.yandex.practicum.Event.LeaveEvent;
import ru.yandex.practicum.Event.ParkEvent;
import ru.yandex.practicum.Event.PassEvent;
import ru.yandex.practicum.ParkingArea.ParkingArea;
import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.Ticket;
import ru.yandex.practicum.Vehicle.ElectroCar;
import ru.yandex.practicum.Vehicle.PremiumCar;
import ru.yandex.practicum.Vehicle.SimpleCar;
import ru.yandex.practicum.Vehicle.Vehicle;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class ParkMate {

    enum Action {
        ENTER,
        LEAVE,
        PASS,
    }
    enum Cars {
        NORMAL,
        ELECTRIC,
        PREMIUM
    }

    static class EventsSimulator {
        private final ArrayList<Ticket> tickets;
        Logger lg;

        private static final Character[] NUMA = {'A', 'B', 'Е', 'K', 'M', 'H', 'O', 'P', 'C', 'T', 'Y', 'X'};

        EventsSimulator() {
            this.tickets = new ArrayList<>();
            this.lg = Logger.getGlobal();
        }

        public void addTicket(Ticket ticket) {
            this.tickets.add(ticket);
        }

        public Event<?> generateEvent() {
            Action action = getRandomAction();

            switch (action) {
                case Action.ENTER -> {
                    StringBuilder builder = new StringBuilder();
                    builder.append(NUMA[(int)(Math.random() * NUMA.length)]);
                    builder.append((int) Math.round(Math.random() * 9));
                    builder.append((int) Math.round(Math.random() * 9));
                    builder.append((int) Math.round(Math.random() * 9));
                    builder.append(NUMA[(int)(Math.random() * NUMA.length)]);
                    builder.append(NUMA[(int)(Math.random() * NUMA.length)]);

                    Cars type = Cars.values()[(int)(Math.random() * Cars.values().length)];
                    Vehicle vehicle;

                    Function<String, Vehicle> create = switch (type) {
                        case NORMAL -> SimpleCar::new;
                        case ELECTRIC -> ElectroCar::new;
                        case PREMIUM -> PremiumCar::new;
                    };

                    vehicle = create.apply(new String(builder));

                    Event<Ticket> event = new ParkEvent(vehicle);
                    lg.info("generate park event: " + event);
                    return event;
                }
                case Action.LEAVE -> {
                    Ticket ticket = takeRandomTicket();
                    Event<Void> event = new LeaveEvent(ticket);
                    lg.info("generate leave event: " + event);
                    return event;
                }
                case Action.PASS -> {
                    Event<Void> event = new PassEvent();
                    lg.info("generate pass event: " + event);
                    return event;
                }
                default -> throw new RuntimeException("unknown action " + action);
            }
        }

        private Action getRandomAction() {
            Action action = Action.values()[(int)(Math.random() * Action.values().length)];
            if (action == Action.LEAVE && tickets.isEmpty()) {
                return Action.PASS;
            }
            return action;
        }

        private Ticket takeRandomTicket() {
            int index = (int)(Math.random() * tickets.size());
            Ticket ticket = tickets.get(index);
            tickets.remove(index);
            return ticket;
        }
    }

    static void main(String[] args) throws InterruptedException {
        Logger lg = Logger.getGlobal();
        lg.info("parking open");
        int totalCountLots = Math.max(20, (int) Math.round(Math.random() * 100));
        int countElectricLots = Math.max((int) Math.round(totalCountLots * 0.1), (int) Math.round(Math.random() * 20));
        int countPremiumLots = Math.max((int) Math.round(totalCountLots * 0.1), (int) Math.round(Math.random() * 20));
        int countSimpleLots = Math.max((int) Math.round(totalCountLots * 0.1), (int) Math.round(Math.random() * 20));
        int totalCountEvents = (int) (Math.random() * 10);

        ParkingArea parkingArea = new ParkingArea(countSimpleLots, countElectricLots, countPremiumLots);
        EventsSimulator simulator = new EventsSimulator();

        while (totalCountEvents > 0) {
            Event<?> event = simulator.generateEvent();
            totalCountEvents--;

            Result<?> result = event.execute(parkingArea);

            if (result.isFailure()) {
                lg.severe("Event " + event + " broken, error=" + result.getErrorCode() + ". skip.");
                continue;
            }

            Optional<?> value = result.getValue();
            if (value.isEmpty()) {
                lg.info("Event " + event + " finished, empty result.");
                continue;
            }
            switch (value.get()) {
                case Ticket ticket -> {
                    simulator.addTicket(ticket);
                    lg.info("Park event " + event + " finished, ticket " + ticket + " was added to wait.");
                }
                default -> {
                    lg.severe("Event " + event + " finished, broken result.");
                }
            }

            Thread.sleep(500);
        }
        lg.info("parking closed");
    }
}
