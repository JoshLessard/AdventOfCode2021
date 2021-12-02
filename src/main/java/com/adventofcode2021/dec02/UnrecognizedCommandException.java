package com.adventofcode2021.dec02;

class UnrecognizedCommandException extends RuntimeException {
    UnrecognizedCommandException( String commandName ) {
        super( "Unrecognized submarine command: " + commandName );
    }
}
