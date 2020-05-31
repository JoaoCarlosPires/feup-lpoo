public class ImpatientStrategy implements OrderingStrategy {
    public ImpatientStrategy() {
    }

    public void wants(StringDrink drink, StringRecipe recipe, StringBar bar) {
        recipe.mix(drink);
    }
    public void happyHourStarted(StringBar bar) {
        bar.startHappyHour();
    }
    public void happyHourEnded(StringBar bar) {
        bar.endHappyHour();
    }
}
