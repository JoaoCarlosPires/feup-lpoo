public class StringReplacer implements StringTransformer{
    private Character oldChar, newChar;

    public StringReplacer(Character char1, Character char2) {
        this.oldChar = char1;
        this.newChar = char2;
    }

    public void execute(StringDrink drink) {

        StringBuffer strNew = new StringBuffer(drink.getText());
        int ln = strNew.length();

        for (int i=0; i<ln; i++) {
            Character c = strNew.charAt(i);
            if (c == oldChar)
                strNew.replace(i, i+1, newChar+"");
        }

        drink.setText(strNew.toString());

       // drink.getText().replace(newChar, oldChar);
    }
}
