package com.adventofcode2021.dec16;

import java.util.List;

class GreaterThanPacket implements Packet {

    private final int version;
    private final Packet packet1;
    private final Packet packet2;

    GreaterThanPacket( int version, List<Packet> subPackets ) {
        this.version = version;
        this.packet1 = subPackets.get( 0 );
        this.packet2 = subPackets.get( 1 );
    }

    @Override
    public int version() {
        return version + packet1.version() + packet2.version();
    }

    @Override
    public long value() {
        return packet1.value() > packet2.value() ? 1 : 0;
    }
}
