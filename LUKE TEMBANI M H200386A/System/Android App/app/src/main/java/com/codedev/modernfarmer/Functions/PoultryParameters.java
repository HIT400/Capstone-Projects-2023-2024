package com.codedev.modernfarmer.Functions;

public class PoultryParameters {

    public static Parameters calculateParams(int desiredChicks) {
        Parameters params = new Parameters();

        // These are just example values; you can adjust them based on your needs.
        params.starterAmount = (desiredChicks / 250) * 150;
        params.growersAmount = desiredChicks / 4;
        params.finisherAmount = desiredChicks / 2;
        params.stresspack = 1;
        params.feeders = desiredChicks / 10;
        params.drinkers = desiredChicks / 10;
        params.roomSize = (desiredChicks * 2); // Assuming 2 square feet per chick and 100 extra square feet.

        return params;
    }


    public static class Parameters {
        public int starterAmount;
        public int growersAmount;
        public int finisherAmount;
        public int stresspack;
        public int feeders;
        public int drinkers;
        public int roomSize;
    }
}
