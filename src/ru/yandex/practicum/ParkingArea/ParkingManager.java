package ru.yandex.practicum.ParkingArea;

import ru.yandex.practicum.ParkingLots.*;
import ru.yandex.practicum.Result.ErrorCode;
import ru.yandex.practicum.Result.Result;
import ru.yandex.practicum.Ticket.*;
import ru.yandex.practicum.Vehicle.*;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ParkingManager implements ParkingOps, ManagerProvider {
    private final ParkingLots simpleLots;
    private final ParkingLots electroLots;
    private final ParkingLots premiumLots;

    private final SimpleLotsOperator simpleLotsOperator;
    private final ElectroLotsOperator electroLotsOperator;
    private final PremiumLotsOperator premiumLotsOperator;

    private static <T extends ParkingLot> void initializeLots(ParkingLots lots, int count, Supplier<T> init) {
//        lots.ensureCapacity(count);
        for(int i=0; i<count; i++) {
            lots.add(init.get());
        }
    }

    public ParkingManager(int countSimpleLots, int countElectricLots, int countPremiumLots) {
        this();
        initializeLots(this.simpleLots, countSimpleLots, SimpleParkingLot::new);
        initializeLots(this.electroLots, countElectricLots, ElectroParkingLot::new);
        initializeLots(this.premiumLots, countPremiumLots, PremiumParkingLot::new);
    }

    private ParkingManager() {
        this.simpleLots = new ParkingLots();
        this.electroLots = new ParkingLots();
        this.premiumLots = new ParkingLots();

        this.simpleLotsOperator = new SimpleLotsOperator(this, simpleLots);
        this.electroLotsOperator = new ElectroLotsOperator(this, electroLots);
        this.premiumLotsOperator = new PremiumLotsOperator(this, simpleLots, premiumLots);
    }

    @Override
    public SimpleLotsOperator getSimpleLotsOperator() { return simpleLotsOperator; }

    @Override
    public ElectroLotsOperator getElectroLotsOperator() {
        return electroLotsOperator;
    }

    @Override
    public PremiumLotsOperator getPremiumLotsOperator() {
        return premiumLotsOperator;
    }

    @Override
    public Result<Ticket> doPark(ParkingLots lots, Vehicle vehicle) {
        int pos = lots.getFirstFree();
        if (-1 == pos) {
            return Result.failure(ErrorCode.NOT_FOUND_EMPTY_LOT);
        }

        ParkingLot lot = lots.get(pos);

        try {
            Result<TicketBuilder<?>> result = lot.enter(vehicle);
            if (result.isFailure()) {
                return Result.failure(result.getErrorCode());
            }
            if (result.getValue().isEmpty()) {
                return Result.failure(ErrorCode.INTERNAL_ERROR);
            }
            TicketBuilder<?> builder = result.getValue().get();
            return Result.success(builder.addPos(pos).addNumber(vehicle.GetNumber()).build());
        } catch (IllegalArgumentException e) {
            return Result.failure(ErrorCode.INTERNAL_ERROR);
        }
    }

    @Override
    public Result<Void> doLeave(ParkingLots lots, Ticket ticket) {
        ParkingLot lot = lots.get(ticket.getPos());
        return lot.leave();
    }
}
