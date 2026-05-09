public class Lasagna {
    public int expectedMinutesInOven(){
        return 40;
    }

    public int remainingMinutesInOven(int minutesInOven){
        return 40 - minutesInOven;
    }

    public int preparationTimeInMinutes(int layers){
        return 2 * layers;
    }

    public int totalTimeInMinutes(int layers,int minutesInOven){
        return preparationTimeInMinutes(layers) + minutesInOven;
    }
}
