import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

//Person object

    public class Person {

        private BooleanProperty invited;

        private StringProperty firstName;

        private StringProperty lastName;

        private StringProperty email;

        private StringProperty likes;
        

        Person(boolean invited, String fName, String lName, String email,String likes) {

            this.invited = new SimpleBooleanProperty(invited);

            this.firstName = new SimpleStringProperty(fName);

            this.lastName = new SimpleStringProperty(lName);

            this.email = new SimpleStringProperty(email);

            this.invited = new SimpleBooleanProperty(invited);
            this.likes = new SimpleStringProperty(likes);

             
            this.invited.addListener(new ChangeListener<Boolean>() {

                public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {

                    System.out.println(firstNameProperty().get() + " invited: " + t1);

                }

            });          
            
            this.firstName.addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
					System.out.println("OldValue:-"+oldValue+"    and    NewValue:-"+newValue);

				}
			});

        }

 

        public Boolean getInvited() {
			return invited.get();
		}



		public String getFirstName() {
			return firstName.get();
		}



		public String getLastName() {
			return lastName.get();
		}



		public String getEmail() {
			return email.get();
		}



		public BooleanProperty invitedProperty() { return invited; }

  

        public StringProperty firstNameProperty() { return firstName; }

 

        public StringProperty lastNameProperty() { return lastName; }

  

        public StringProperty emailProperty() { return email; }

 

        public void setLastName(String lastName) { this.lastName.set(lastName); }

  

        public void setFirstName(String firstName) { this.firstName.set(firstName); }

   

        public void setEmail(String email) { this.email.set(email); }
        
        public String getLikes() {
            return likes.get();
        }

        public void setLikes(String likes) {
            this.likes.set(likes);
        }
        
        
        



		@Override
		public String toString() {
			return "Person [invited=" + invited + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
					+ email + ", likes=" + likes + "]";
		}



		
        
        
    }

 

