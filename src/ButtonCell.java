import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonCell extends TableCell<Person, Person> {
	// icons for non-commercial use with attribution from: http://www.iconarchive.com/show/veggies-icons-by-iconicon/bananas-icon.html and http://www.iconarchive.com/show/collection-icons-by-archigraphs.html
    private final Image coffeeImage = new Image(
      "http://icons.iconarchive.com/icons/archigraphs/collection/48/Coffee-icon.png"
    );
    private final Image fruitImage = new Image(
      "http://icons.iconarchive.com/icons/iconicon/veggies/48/bananas-icon.png"
    );
    private Label setonaction;
    
    public ButtonCell (Label actiontaken)
    {
    	this.setonaction=actiontaken;
    }
	final ImageView buttonGraphic = new ImageView();
    final Button button = new Button(); {
      button.setGraphic(buttonGraphic);
      button.setMinWidth(130);
    }
    
    
    
    @Override public void updateItem(final Person person, boolean empty) {
        super.updateItem(person, empty);
        if (person != null) {
         switch (person.getLikes().toLowerCase()) {
           case "fruit": 
            button.setText("Buy fruit");
            buttonGraphic.setImage(fruitImage);
            break;

          default:
            button.setText("Buy coffee");
            buttonGraphic.setImage(coffeeImage);
            break;
          }

           setGraphic(button);
        
         } else {
          setGraphic(null);
        }
     
	
    
    
	
	
	button.setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent event) {
        	setonaction.setText("Bought " + person.getLikes().toLowerCase() + " for: " + person.getFirstName() + " " + person.getLastName());
     }
     });
  
}
}
