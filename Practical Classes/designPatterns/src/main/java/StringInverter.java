public class StringInverter implements StringTransformer {

    public StringInverter() {
    }

    public void execute(StringDrink drink) {
        StringBuffer strNew = new StringBuffer(drink.getText());
        strNew.reverse();
        drink.setText(strNew.toString());
    }
}
