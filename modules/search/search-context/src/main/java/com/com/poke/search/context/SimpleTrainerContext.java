package com.com.poke.search.context;

public final class SimpleTrainerContext implements TrainerContext {

    private final int tsv;

    private final boolean hasShinyCharm;

    public SimpleTrainerContext(final int tid, final int sid, final boolean hasShinyCharm) {

        this.tsv = (tid ^ sid) >>> 4;
        this.hasShinyCharm = hasShinyCharm;
    }

    public SimpleTrainerContext(final int tsv, final boolean hasShinyCharm) {

        this.tsv = tsv;
        this.hasShinyCharm = hasShinyCharm;
    }

    @Override
    public int shinyValue() {

        return this.tsv;
    }

    @Override
    public boolean hasShinyCharm() {

        return this.hasShinyCharm;
    }
}
