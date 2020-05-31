public class HumanClient implements Client, BarObserver{
    private OrderingStrategy strategy;

    public HumanClient(OrderingStrategy strategy) {
        this.strategy = strategy;
    }

    public void wants(StringDrink drink, StringRecipe recipe, StringBar bar) {
        recipe.mix(drink);
    }

    public void happyHourStarted(Bar bar) {
        bar.startHappyHour();
    }

    public void happyHourEnded(Bar bar) {
        bar.endHappyHour();
    }
}
