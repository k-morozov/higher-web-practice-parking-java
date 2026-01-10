package ru.yandex.practicum.ParkingLots;

import java.util.ArrayList;

public class ParkingLots {
    // @todo linear complexity is enough here.
    private final ArrayList<ParkingLot> lots;

    public ParkingLots() {
        this.lots = new ArrayList<>();
    }

    public void add(ParkingLot lot) {
        lots.add(lot);
    }

    public boolean hasFree() {
        return lots.stream().anyMatch(ParkingLot::isFree);
    }

    public int getFirstFree() {
        int pos = 0;
        for (ParkingLot lot : lots) {
            if (lot.isFree()) {
                return pos;
            }
            pos++;
        }

        return -1;
    }

    public ParkingLot get(int pos) {
        if (pos < 0 || pos >= lots.size()) {
            throw new IllegalArgumentException("Broken pos " + pos);
        }
        return lots.get(pos);
    }
}
