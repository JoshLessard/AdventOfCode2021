package com.adventofcode2021.dec08;

import java.util.List;

class SignalInput {

    private final List<Signal> signalPatterns;
    private final List<Signal> outputValues;

    SignalInput( List<Signal> signalPatterns, List<Signal> outputValues ) {
        this.signalPatterns = signalPatterns;
        this.outputValues = outputValues;
    }

    List<Signal> signalPatterns() {
        return signalPatterns;
    }

    List<Signal> outputValues() {
        return outputValues;
    }
}
