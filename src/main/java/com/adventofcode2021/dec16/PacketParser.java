package com.adventofcode2021.dec16;

import java.util.ArrayList;
import java.util.List;

class PacketParser {

    private final BitQueue bits = new BitQueue();

    PacketParser( String hexadecimalInput ) {
        hexadecimalInput.chars()
            .mapToObj( c -> Nibble.of( (char) c ) )
            .forEach( bits::add );
    }

    Packet parse() {
        return parsePacket();
    }

    private Packet parsePacket() {
        int version = bits.nextBits( 3 );
        int typeId = bits.nextBits( 3 );
        switch ( typeId ) {
            case 0:
                return new SumPacket( version, parseSubPackets() );
            case 1:
                return new ProductPacket( version, parseSubPackets() );
            case 2:
                return new MinimumPacket( version, parseSubPackets() );
            case 3:
                return new MaximumPacket( version, parseSubPackets() );
            case 4:
                return new LiteralValuePacket( version, parseLiteralValue() );
            case 5:
                return new GreaterThanPacket( version, parseSubPackets() );
            case 6:
                return new LessThanPacket( version, parseSubPackets() );
            case 7:
                return new EqualToPacket( version, parseSubPackets() );
            default:
                throw new IllegalArgumentException( "Unrecognized type ID: " + typeId );
        }
    }

    private long parseLiteralValue() {
        long literalValue = 0;
        while ( true ) {
            boolean isLastGroup = bits.nextBits( 1 ) == 0;
            literalValue *= 16;
            literalValue += bits.nextBits( 4 );
            if ( isLastGroup ) {
                return literalValue;
            }
        }
    }

    private List<Packet> parseSubPackets() {
        List<Packet> packets = new ArrayList<>();
        int lengthType = bits.nextBits( 1 );
        if ( lengthType == 0 ) {
            int numberOfSubPacketBits = bits.nextBits( 15 );
            int expectedNumberOfBitsRemaining = bits.size() - numberOfSubPacketBits;
            while ( bits.size() > expectedNumberOfBitsRemaining ) {
                packets.add( parsePacket() );
            }
            assert bits.size() == expectedNumberOfBitsRemaining;
        } else if ( lengthType == 1 ) {
            int numberOfSubPackets = bits.nextBits( 11 );
            for ( int i = 0; i < numberOfSubPackets; ++i ) {
                packets.add( parsePacket() );
            }
        }
        return packets;
    }
}
