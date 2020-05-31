import java.util.List;

public class SmartStrategy implements OrderingStrategy {
    private List<StringDrink> drinks;
    public SmartStrategy() {
    }

    public void wants(StringDrink drink, StringRecipe recipe, StringBar bar) {
        drinks.add(drink);
        if (bar.isHappyHour()) {
            for (StringDrink drinks : drinks)
                recipe.mix(drinks);
            drinks.clear();
        }
    }

    public void happyHourStarted(StringBar bar) {bar.endHappyHour();}
    public void happyHourEnded(StringBar bar) {bar.endHappyHour();}
}
