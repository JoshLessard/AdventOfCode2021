package com.adventofcode2021.dec12;

import java.util.Objects;

class Cave {

    private final String name;
    private final boolean isSmall;

    static Cave from( String name ) {
        return new Cave( name, isAllLowerCase( name ) );
    }

    private static boolean isAllLowerCase( String name ) {
        return name.chars().allMatch( Character::isLowerCase );
    }

    private Cave( String name, boolean isSmall ) {
        this.name = name;
        this.isSmall = isSmall;
    }

    boolean isSmall() {
        return isSmall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cave)) return false;
        Cave cave = (Cave) o;
        return isSmall == cave.isSmall && name.equals(cave.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isSmall);
    }
}
