package com.com.poke.ui.components.widgets;

public final class CombinedInheritanceResult<T> {

    private final T result;
    private final Object inheritanceType;

    public CombinedInheritanceResult(final T result, final Object inheritanceType) {

        this.result = result;
        this.inheritanceType = inheritanceType;
    }

    T getResult() {

        return this.result;
    }

    boolean fromMale() {

        return this.inheritanceType != null && this.inheritanceType.toString().contains("Male");
    }

    boolean fromFemale() {

        return this.inheritanceType != null && this.inheritanceType.toString().contains("Female");
    }

}
