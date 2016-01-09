package module4;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class Controller
{
    public static final String PATH = "\\data\\us_babynames_by_year\\";

    public Controller()
    {
        new_name   = new SimpleStringProperty();
        message    = new SimpleStringProperty();

        baby_birth = new BabyBirths(PATH);
    }

    public void HandleSubmitButton(ActionEvent event)
    {
        if(first_name.getText().isEmpty() ||
           birth_year.getText().isEmpty() ||
           new_birth_year.getText().isEmpty()) SetMessage("Please fill in all text field with appropriate values. Gender: " + gender);
        else
        {
            String name     = first_name.getText();
            int year        = Integer.parseInt(birth_year.getText());
            int new_year    = Integer.parseInt(new_birth_year.getText());

            String new_name = baby_birth.WhatIsNameInYear(name, year, new_year, gender);
            SetNewName(new_name);
        }
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
