package com.adventofcode2021.dec16;

import java.util.List;

class MinimumPacket implements Packet {

    private final int version;
    private final List<Packet> subPackets;

    MinimumPacket( int version, List<Packet> subPackets ) {
        this.version = version;
        this.subPackets = List.copyOf( subPackets );
    }

    @Override
    public int version() {
        return version + subPacketVersion();
    }

    private int subPacketVersion() {
        return subPackets.stream()
            .mapToInt( Packet::version )
            .sum();
    }

    @Override
    public long value() {
        return subPackets.stream()
            .mapToLong( Packet::value )
            .min()
            .orElseThrow();
    }
}
