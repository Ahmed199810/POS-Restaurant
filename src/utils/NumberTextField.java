package utils;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField
{

    private int x = 0;
    @Override
    public void replaceText(int start, int end, String text)
    {
        if (validate(text))
        {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text)
    {
        if (validate(text))
        {
            super.replaceSelection(text);
        }
    }

    private boolean validate(String text)
    {
        if (text.equals("")){
            x = 0;
        }
        boolean b;
        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) == '.'){
                x++;
                System.out.println(x);
                System.out.println(text);
            }
        }
        if (x > 1){
            b = true && text.matches("[0-9]*");
        }else {
            b = true;
        }
        return text.matches("[0-9.]*") && b;
    }
}