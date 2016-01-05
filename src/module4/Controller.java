package module4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller
{
    public void HandleSayHi(ActionEvent actionEvent)
    {
        System.out.println("Hi " + first_name.getText());
    }

    @FXML private TextField first_name;
}
