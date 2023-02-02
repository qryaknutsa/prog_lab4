package story;

public enum Noise {
    DOG(" лает собака", 2),
    PEOPLE(" люди тихо разговаривают", 3),
    LAUGH(" смеётся ребёнок", 4),
    CRY(" плачет ребёнок", 5),
    DISHES(" кто-то моет посудой", 6),
    PIANO(" кто-то бренчит на пианино", 7);

    private final String Russian;
    int i = 1;

    Noise(String Russian, int i) {
        this.Russian = Russian;
        this.i = i;
    }

    public String getRussianNoise() {
        return Russian;
    }

    public static Noise getNoise(int i) {
        return Noise.values()[i];
    }
}
