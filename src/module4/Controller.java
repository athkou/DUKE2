package module4;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Controller
{
    public static final String PATH = "\\data\\us_babynames_by_year\\";

    public Controller()
    {
        new_name   = new SimpleStringProperty();
        message    = new SimpleStringProperty();

        baby_birth = new BabyBirths(PATH);
    }

    /**
     * Checks the text fields and displays the new name
     * @param event
     */
    public void HandleSubmitButton(ActionEvent event)
    {
        if(first_name.getText().isEmpty() ||
           birth_year.getText().isEmpty() ||
           new_birth_year.getText().isEmpty()) SetMessage("Please fill in all text field with appropriate values.");
        else
        {
            String name = GetInput(first_name,
                                   "^[a-zA-Z]+$",
                                   "The name can only contain the letters[a-zA-Z]");
            int year     = 0;
            int new_year = 0;

            String temp_year = GetInput(birth_year,
                                        "^[0-9]{4}",
                                        "The birth year text field accepts only numbers\nin the format yyyy ranging from 1880 to 2014.");

            if(temp_year != null) year = Integer.parseInt(birth_year.getText());

            String temp_new_year = GetInput(new_birth_year,
                                 "^[0-9]{4}",
                                 "The new year text field accepts only numbers\nin the format yyyy ranging from 1880 to 2014.");

            if(temp_new_year != null) new_year = Integer.parseInt(new_birth_year.getText());

            boolean right_range = CheckCorrectYearRange(year, new_year);

            if(!right_range)
            {
                SetMessage("Both \"birth year\" and \"new year\" text fields\nneed to have a value from 1880 to 2014.");
                return ;
            }

            gender = "f";

            if(name != null && temp_year != null && temp_new_year != null)
            {
                String new_name = baby_birth.WhatIsNameInYear(name, year, new_year, gender);
                SetNewName(new_name);
                SetMessage("");
            }
        }
    }

    /**
     * The method validates the input and returns null if the input is invalid or a String otherwise.
     *
     * @param field A TextField variable whose text we want to validate
     * @param reg_ex A String variable containing a regular expression
     * @param message A String variable displaying a message if the TextField has not a valid input
     * @return null or a String
     */
    public String GetInput(TextField field, String reg_ex, String message)
    {
        boolean input = ValidateInput(field.getText(), reg_ex);
        if(!input)
        {
            ClearInput(message, field);
            return null;
        }
        else return field.getText();
    }

    /**
     * The method checks if the two variables have a correct value
     * which is in the range[1880, 2014].
     *
     * @param year An integer variable containing the birth year
     * @param new_year An integer variable containing the new birth year
     * @return true or false
     */
    public boolean CheckCorrectYearRange(int year, int new_year)
    {
        int begin = 1879;
        int end   = 2014;

        return year > begin && year <= end && new_year > begin && new_year <= end;
    }

    /**
     * The method checks if the input matches the regular expression
     * and return true or false.
     *
     * @param input A String variable containing the input from the user
     * @param reg_ex A String variable containing a regular expression.
     * @return true or false
     */
    public boolean ValidateInput(String input, String reg_ex)
    {
        Pattern pattern = Pattern.compile(reg_ex);
        Matcher matcher = pattern.matcher(input);

        return matcher.find();
    }

    /**
     * The method prints a message a clear the input from the TextField.
     *
     * @param message A String variable containing a message
     * @param field A TextField variable
     */
    public void ClearInput(String message, TextField field)
    {
        SetMessage(message);
        field.clear();
    }

    public String getNew_name()              { return new_name.get(); }
    public String getMessage()               { return message.get();  }

    public StringProperty new_nameProperty() { return new_name;       }
    public StringProperty messageProperty()  { return message;        }

    public void SetNewName(String new_name) { this.new_name.set(new_name); }
    public void SetMessage(String message)  { this.message.set(message);   }

    @FXML private TextField first_name;
    @FXML private TextField birth_year;
    @FXML private TextField new_birth_year;
    @FXML private ToggleGroup gender_group;

    private StringProperty new_name;
    private StringProperty message;
    private BabyBirths baby_birth;

    private String gender;
}



