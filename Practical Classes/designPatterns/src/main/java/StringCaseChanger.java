public class StringCaseChanger implements StringTransformer{

    public StringCaseChanger() {
    }

    public void execute(StringDrink drink) {

        StringBuffer strNew = new StringBuffer(drink.getText());
        int ln = strNew.length();

        for (int i=0; i<ln; i++) {
            Character c = strNew.charAt(i);
            if (Character.isLowerCase(c))
                strNew.replace(i, i+1, Character.toUpperCase(c)+"");
            else
                strNew.replace(i, i+1, Character.toLowerCase(c)+"");
        }

        drink.setText(strNew.toString());
    }

}
