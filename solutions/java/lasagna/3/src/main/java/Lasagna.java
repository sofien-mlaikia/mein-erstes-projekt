public class Lasagna {

    private static final int EXPECTED_MINUTES_IN_OVEN = 40;
    private static final int PREPARATION_TIME_PER_LAYER = 2;
    
    public int expectedMinutesInOven(){
        return 40;
    }

    public int remainingMinutesInOven(int minutesInOven){
        return expectedMinutesInOven() - minutesInOven;
    }

    public int preparationTimeInMinutes(int layers){
        return 2 * layers;
    }

    public int totalTimeInMinutes(int layers,int minutesInOven){
        return preparationTimeInMinutes(layers) + minutesInOven;
    }
}
