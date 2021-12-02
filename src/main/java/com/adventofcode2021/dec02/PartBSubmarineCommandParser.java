package com.adventofcode2021.dec02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PartBSubmarineCommandParser implements SubmarineCommandParser {

    private static final Pattern COMMAND_PATTERN = Pattern.compile( "^([a-z]+) (\\d+)$" );

    @Override
    public SubmarineCommand parse( String raw ) {
        Matcher matcher = COMMAND_PATTERN.matcher( raw );
        if ( matcher.matches() ) {
            String commandName = matcher.group( 1 );
            int distance = Integer.parseInt( matcher.group( 2 ) );
            switch ( commandName ) {
                case "forward":
                    return new MoveAndDiveSubmarineCommand( distance );
                case "down":
                    return new ChangeAimSubmarineCommand( distance );
                case "up":
                    return new ChangeAimSubmarineCommand( -distance );
                default:
                    throw new UnrecognizedCommandException( commandName );
            }
        }
        throw new IllegalArgumentException();
    }
}
