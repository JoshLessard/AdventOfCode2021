package com.adventofcode2021.dec16;

class LiteralValuePacket implements Packet {

    private final int version;
    private final long literalValue;

    LiteralValuePacket( int version, long literalValue ) {
        this.version = version;
        this.literalValue = literalValue;
    }

    @Override
    public int version() {
        return version;
    }

    @Override
    public long value() {
        return literalValue;
    }
}
