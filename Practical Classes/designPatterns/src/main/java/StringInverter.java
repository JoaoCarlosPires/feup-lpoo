public class StringInverter implements StringTransformer {

    @Override
    public void execute(StringDrink drink) {
        StringBuffer strNew = new StringBuffer(drink.getText());
        strNew.reverse();
        drink.setText(strNew.toString());
    }
}
