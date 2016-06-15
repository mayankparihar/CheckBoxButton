
/**

 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.

 * All rights reserved. Use is subject to license terms.

 */

import java.net.URL;
import java.util.ResourceBundle;


import javafx.application.Application;

import javafx.scene.Group;

import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.stage.Stage;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;

import javafx.beans.property.StringProperty;

import javafx.beans.property.SimpleStringProperty;

import javafx.beans.value.ChangeListener;

import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

 

/**

 * A simple table that uses cell factories to add a control to a table

 * column and to enable editing of first/last name and email.

 *

 * @see javafx.scene.control.TableCell

 * @see javafx.scene.control.TableColumn

 * @see javafx.scene.control.TablePosition

 * @see javafx.scene.control.TableRow

 * @see javafx.scene.control.TableView

 */

public class TableviewWIthCheckbox extends Application implements Initializable {
	
	
	  private static ObservableList<Person> data = FXCollections.observableArrayList( );
      private static   Label actionTaken = new Label();
      private static VBox vbox= new VBox();
      private void init(Stage primaryStage) {

        Group root = new Group();

        primaryStage.setScene(new Scene(root));

      

        //"Invited" column

        TableColumn invitedCol = new TableColumn<Person, Boolean>();

        invitedCol.setText("Invited");

        invitedCol.setMinWidth(50);

        invitedCol.setCellValueFactory(new PropertyValueFactory("invited"));

        invitedCol.setCellFactory(new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {

 

            public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> p) {

                return new CheckBoxTableCell<Person, Boolean>();

            }

        });

        //"First Name" column

        TableColumn firstNameCol = new TableColumn();

        firstNameCol.setText("First");

        firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));

        //"Last Name" column

        TableColumn lastNameCol = new TableColumn();

        lastNameCol.setText("Last");

        lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));

        //"Email" column

        TableColumn emailCol = new TableColumn();

        emailCol.setText("Email");

        emailCol.setMinWidth(200);

        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        
        
        TableColumn<Person, Person> btnCol = new TableColumn<>("Gifts");
        btnCol.setMinWidth(150);
        btnCol.setCellValueFactory(new Callback<CellDataFeatures<Person, Person>, ObservableValue<Person>>() {
          @Override public ObservableValue<Person> call(CellDataFeatures<Person, Person> features) {
        	
        	  //to check that return value is person object
        	  //  System.out.println(features.getValue());
              return new ReadOnlyObjectWrapper(features.getValue());
             
          }
        });
        

 

        //Set cell factory for cells that allow editing

        Callback<TableColumn, TableCell> cellFactory =

                new Callback<TableColumn, TableCell>() {

 

                    public TableCell call(TableColumn p) {

                        return new EditingCell();

                    }

                };

        emailCol.setCellFactory(cellFactory);

        firstNameCol.setCellFactory(cellFactory);

        lastNameCol.setCellFactory(cellFactory);
        
        btnCol.setSortable(false);
        /* btnCol.setComparator(new Comparator<Person>() {
           @Override public int compare(Person p1, Person p2) {
             return p1.getLikes().compareTo(p2.getLikes());
           }
         });*/
         btnCol.setCellFactory(new Callback<TableColumn<Person, Person>, TableCell<Person, Person>>() {
           @Override public TableCell<Person, Person> call(TableColumn<Person, Person> btnCol) {
            
             
			return new ButtonCell(actionTaken);
         }
         });

 

        //Set handler to update ObservableList properties. Applicable if cell is edited

        updateObservableListProperties(emailCol, firstNameCol, lastNameCol);

 

        TableView tableView = new TableView();

        tableView.setItems(data);

        //Enabling editing

        tableView.setEditable(true);
        //GetSelectedRow
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>(){

			@Override
			public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
				// TODO Auto-generated method stub
				
			Person newperson;	
				newperson=(Person)tableView.getSelectionModel().selectedItemProperty().getValue();
				System.out.println(newperson);
				
			}
        	
        	
        	
        	
        	
        });

        tableView.getColumns().addAll(invitedCol, firstNameCol, lastNameCol, emailCol,btnCol);
        vbox.getChildren().addAll(tableView,actionTaken);
        root.getChildren().add(vbox); 

    }

 

  private void updateObservableListProperties(TableColumn emailCol, TableColumn firstNameCol,

            TableColumn lastNameCol) {

        //Modifying the email property in the ObservableList

        emailCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {           

            @Override public void handle(CellEditEvent<Person, String> t) {

                ((Person) t.getTableView().getItems().get(

                        t.getTablePosition().getRow())).setEmail(t.getNewValue());

            }

        });

        //Modifying the firstName property in the ObservableList

        firstNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {          

            @Override public void handle(CellEditEvent<Person, String> t) {

                ((Person) t.getTableView().getItems().get(

                        t.getTablePosition().getRow())).setFirstName(t.getNewValue());

            }

        });

        //Modifying the lastName property in the ObservableList

        lastNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {           

            @Override public void handle(CellEditEvent<Person, String> t) {

                ((Person) t.getTableView().getItems().get(

                        t.getTablePosition().getRow())).setLastName(t.getNewValue());

            }

        });

    }    

     

   

 

    @Override public void start(Stage primaryStage) throws Exception {
    	

        init(primaryStage);

        primaryStage.show();

    }

    public static void main(String[] args) { 
    	data.addAll(
    			
    			
      			 new Person(true, "Jacob", "Smith", "jacob.smith@example.com","coffee"),

                   new Person(true, "Isabella", "Johnson", "isabella.johnson@example.com","fruit"),

                   new Person(true, "Ethan", "Williams", "ethan.williams@example.com","coffee"),

                   new Person(true, "Emma", "Jones", "emma.jones@example.com","Fruit"),

                   new Person(true, "Michael", "Brown", "michael.brown@example.com","fruit")
      			
      			   			
      			
      			);
    	
    		launch(args);
    	
    	
    	
    	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
