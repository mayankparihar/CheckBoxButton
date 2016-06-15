import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;

//CheckBoxTableCell for creating a CheckBox in a table cell

    public class CheckBoxTableCell<S, T> extends TableCell<S, T> {

        private final CheckBox checkBox;

        private ObservableValue<T> ov;

 

        public CheckBoxTableCell() {

            this.checkBox = new CheckBox();

            this.checkBox.setAlignment(Pos.CENTER);

 

            setAlignment(Pos.CENTER);

            setGraphic(checkBox);

        } 

         

        @Override public void updateItem(T item, boolean empty) {

            super.updateItem(item, empty);
             // if there is no entry for this instance variable in object then cell must be empty
            if (empty) {

                setText(null);

                setGraphic(null);

            } 
            else {

                setGraphic(checkBox);
                //if observable value is boolean then 
                if (ov instanceof BooleanProperty) {

                    checkBox.selectedProperty().unbindBidirectional((BooleanProperty) ov);
                                       
                }
                
               

                ov = getTableColumn().getCellObservableValue(getIndex());
                
               if (ov instanceof BooleanProperty) {

                    checkBox.selectedProperty().bindBidirectional((BooleanProperty) ov);

                }

            }

        }

    }

 

   
 

       