import java.util.List;

public class StringRecipe {

    private List<StringTransformer> strings;

    public StringRecipe(List<StringTransformer> strings) {
        this.strings = strings;
    }

    public void mix(StringDrink drink) {
        for (StringTransformer strTrans : strings) {
            strTrans.execute(drink);
        }
    }

}
