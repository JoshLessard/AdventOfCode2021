package com.adventofcode2021.dec18;

import java.nio.charset.StandardCharsets;

class SnailfishNumberParser {

    SnailfishNumber parse(String raw ) {
        CharacterStream characters = new CharacterStream( raw.getBytes( StandardCharsets.UTF_8 ) );
        return parse( characters );
    }

    private SnailfishNumber parse( CharacterStream characters ) {
        if ( characters.peek() == '[' ) {
            return parsePair( characters );
        } else {
            return parseRegular( characters );
        }
    }

    private SnailfishNumber parsePair( CharacterStream characters ) {
        characters.next(); // [
        SnailfishNumber left = parse( characters );
        characters.next(); // ,
        SnailfishNumber right = parse( characters );
        characters.next(); // ]
        return SnailfishNumber.ofPair( left, right );
    }

    private SnailfishNumber parseRegular( CharacterStream characters ) {
        long regularValue = 0L;
        while ( Character.isDigit( characters.peek() ) ) {
            regularValue *= 10;
            regularValue += characters.next() - '0';
        }
        return SnailfishNumber.ofRegularValue( regularValue );
    }
}
