package com.adventofcode2021.dec22;

import com.adventofcode2021.common.Point3d;

enum ReactorCommand {

    ON {
        @Override
        void applyTo( ReactorCore reactorCore, Point3d point ) {
            reactorCore.turnOn( point );
        }
    },
    OFF {
        @Override
        void applyTo( ReactorCore reactorCore, Point3d point ) {
            reactorCore.turnOff( point );
        }
    };

    abstract void applyTo( ReactorCore reactorCore, Point3d point );

    static ReactorCommand parse( String command ) {
        switch ( command ) {
            case "on":
                return ON;
            case "off":
                return OFF;
            default:
                throw new IllegalArgumentException( "Unrecognized command: " + command );
        }
    }
}
