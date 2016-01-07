package module4;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller
{
    public static final String PATH = "\\data\\us_babynames_by_year\\";

    public Controller()
    {
        new_name   = new SimpleStringProperty();
        baby_birth = new BabyBirths(PATH);
    }

    public void HandleSubmitButton(ActionEvent event)
    {
        String name     = first_name.getText();
        int year        = Integer.parseInt(birth_year.getText());
        int new_year    = Integer.parseInt(new_birth_year.getText());
        String gender   = "f";

        String new_name = baby_birth.WhatIsNameInYear(name, year, new_year, gender);
        SetNewName(new_name);
    }

    public String getNew_name()              { return new_name.get(); }
    public StringProperty new_nameProperty() { return new_name;       }

    public void SetNewName(String new_name) { this.new_name.set(new_name); }

    @FXML private TextField first_name;
    @FXML private TextField birth_year;
    @FXML private TextField new_birth_year;

    private StringProperty new_name;
    private BabyBirths baby_birth;
}
