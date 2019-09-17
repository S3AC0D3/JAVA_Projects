/**
 * Title: Boola Boola University
 * Programmer: Werner Ordonez
 * Details: A program similar to web advisor where a student can go through
 * the admissions process and get admitted into the school. Then the student 
 * can register for classes. And finally the program will provide a report 
 * presenting the students report.
 *
 */
package boolawerner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author wernerordonez
 */
public class BoolaWerner extends Application {

    @Override
    public void start(Stage primaryStage) {

        //creade a border pane
        BorderPane pane = new BorderPane();

        //create menu bar
        MenuBar mb = new MenuBar();

        //create an admissions menu
        Menu admissions = new Menu("Admission");
        MenuItem matriculated = new MenuItem("Matriculated");
        MenuItem nonMatriculated = new MenuItem("Non-matriculated");
        MenuItem quit = new MenuItem("Quit");
        admissions.getItems().addAll(matriculated, nonMatriculated,
                new SeparatorMenuItem(), quit);
        mb.getMenus().add(admissions);

        //create the registration menu
        Menu registration = new Menu("Registration");
        MenuItem fullTime = new MenuItem("Full-time");
        MenuItem partTime = new MenuItem("Part-time");
        MenuItem nonCredit = new MenuItem("Non-Credit");
        registration.getItems().addAll(fullTime, partTime, nonCredit);
        mb.getMenus().add(registration);

        //create the reports menu
        Menu reports = new Menu("Reports");
        MenuItem receivables = new MenuItem("Receivables");
        MenuItem classSchedule = new MenuItem("Class Schedule");
        reports.getItems().addAll(receivables, classSchedule);
        mb.getMenus().add(reports);

        //set menu bar to top
        pane.setTop(mb);
        
        //create a class for the student 
        class Student {
            
            // create strings for students information
            private String SSN;
            private String FirstName;
            private String MI;
            private String LastName;
            private String Street;
            private String City;
            private String State;
            private String Zipcode;
            private String Date;
            private String YearClass;
            private String Degree;
            private String HS;
            private String IR;
            
            //construct students structs 
            public Student(){}
            
            public Student(String SSN, String FirstName, String MI, 
                    String LastName, String Street, String City, String State, 
                    String Zipcode, String Date, String YearClass, 
                    String Degree, String HS, String IR) 
            {
                this.SSN = SSN;
                this.FirstName = FirstName;
                this.MI = MI;
                this.LastName = LastName;
                this.Street = Street;
                this.City = City;
                this.State = State;
                this.Zipcode = Zipcode;
                this.Date = Date;
                this.YearClass = YearClass;
                this.Degree = Degree;
                this.HS = HS;
                this.IR = IR;
            }
            
            //functions to call the students information
            public String getSSN() {
                return SSN;
            }
            public String getFirstName() {
                return FirstName;
            }
            public String getMI() {
                return MI;
            }
            public String getLastName() {
                return LastName;
            }
            public String getStreet() {
                return Street;
            }
            public String getCity() {
                return City;
            }
            public String getState() {
                return State;
            }
            public String getZipcode() {
                return Zipcode;
            }
            public String getDate() {
                return Date;
            }
            public String getYearClass() {
                return YearClass;
            }
            public String getDegree() {
                return Degree;
            }
            public String getHS() {
                return HS;
            }
            public String getIR() {
                return IR;
            }

        }

        class Admissions {

            //creates dialog Stage for sending warning to user
            Stage warningStage = new Stage();

            //create textfields
            private TextField ssnTxtFld = new TextField();
            private TextField firstNameTxtFld = new TextField();
            private TextField middleInitialTxtFld = new TextField();
            private TextField lastNameTxtFld = new TextField();
            private TextField streetAddressTxtFld = new TextField();
            private TextField stateTxtFld = new TextField();
            private TextField cityTxtFld = new TextField();
            private TextField zipCodeTxtFld = new TextField();
            private TextField todaysDateTxtFld = new TextField();
            private ComboBox<String> yearMatriculatedcb = new ComboBox<>();
            private ComboBox<String> degreecb = new ComboBox<>();
            private CheckBox highSchool = new CheckBox("High Scool Diploma");
            private CheckBox immuRecord = new CheckBox("Immunization Record");
            private CheckBox immuRecordNonM = new CheckBox("Immunization Record "
                    + "(9+ credits)");

            //create a button
            Button submitBtn = new Button("Submit");

            //create array for the combo boxes
            private String[] yearMatricualted = {"Freshman", "Sophomore",
                "Junior", "Senior"};
            private String[] degree = {"Associate of Science in "
                + "Computer Programing", "Assicoaite of Arts in Humanitites"};
            ObservableList<String> items
                    = FXCollections.observableArrayList(yearMatricualted);
            ObservableList<String> degreeItems
                    = FXCollections.observableArrayList(degree);

            //create labels for all items
            protected Label ssn = new Label("SSN: ");
            protected Label firstNameLB = new Label("First Name: ");
            protected Label lastNameLB = new Label("Last Name: ");
            protected Label middleNameLB = new Label("Middle Initial: ");
            protected Label streetLB = new Label("Street: ");
            protected Label stateLB = new Label("State: ");
            protected Label cityLB = new Label("City: ");
            protected Label zipcodeLB = new Label("Zip Code: ");
            protected Label todaysDateLB = new Label("Today's Date: ");
            protected Label dateLB = new Label("Date: ");
            protected Label yearOfMatLB = new Label("Year Of Matriculated: ");
            protected Label degreeLB = new Label("Degree: ");

            //method that create gridpane of matriculated registration.
            public GridPane MatriculatedPane() {

                //create matricualted pane
                GridPane pane = new GridPane();
                pane.setPadding(new Insets(10, 10, 10, 10));
                pane.setVgap(5.5);
                pane.setHgap(5.5);
                yearMatriculatedcb.getItems().addAll(yearMatricualted);
                degreecb.getItems().addAll(degree);

                //add node to the pane
                pane.add(ssn, 0, 0);
                pane.add(ssnTxtFld, 1, 0);
                pane.add(firstNameLB, 0, 1);
                pane.add(firstNameTxtFld, 1, 1);
                pane.add(middleNameLB, 0, 2);
                pane.add(middleInitialTxtFld, 1, 2);
                pane.add(lastNameLB, 0, 3);
                pane.add(lastNameTxtFld, 1, 3);
                pane.add(streetLB, 0, 4);
                pane.add(streetAddressTxtFld, 1, 4);
                pane.add(cityLB, 0, 5);
                pane.add(cityTxtFld, 1, 5);
                pane.add(stateLB, 0, 6);
                pane.add(stateTxtFld, 1, 6);
                pane.add(zipcodeLB, 0, 7);
                pane.add(zipCodeTxtFld, 1, 7);
                pane.add(todaysDateLB, 0, 8);
                pane.add(todaysDateTxtFld, 1, 8);
                pane.add(yearOfMatLB, 0, 9);
                pane.add(yearMatriculatedcb, 1, 9);
                pane.add(degreeLB, 0, 10);
                pane.add(degreecb, 1, 10);
                pane.add(highSchool, 1, 11);
                pane.add(immuRecord, 1, 12);
                pane.add(submitBtn, 3, 14);

                //activate the submit button
                submitBtn.setOnAction(e -> {

                });

                return pane;
            }

            public GridPane nonMatriculatedPane() {

                //create matricualted pane
                GridPane pane = new GridPane();
                pane.setPadding(new Insets(10, 10, 10, 10));
                pane.setVgap(5.5);
                pane.setHgap(5.5);

                //add node to the pane
                pane.add(ssn, 0, 0);
                pane.add(ssnTxtFld, 1, 0);
                pane.add(firstNameLB, 0, 1);
                pane.add(firstNameTxtFld, 1, 1);
                pane.add(middleNameLB, 0, 2);
                pane.add(middleInitialTxtFld, 1, 2);
                pane.add(lastNameLB, 0, 3);
                pane.add(lastNameTxtFld, 1, 3);
                pane.add(streetLB, 0, 4);
                pane.add(streetAddressTxtFld, 1, 4);
                pane.add(cityLB, 0, 5);
                pane.add(cityTxtFld, 1, 5);
                pane.add(stateLB, 0, 6);
                pane.add(stateTxtFld, 1, 6);
                pane.add(zipcodeLB, 0, 7);
                pane.add(zipCodeTxtFld, 1, 7);
                pane.add(immuRecordNonM, 1, 8);
                pane.add(submitBtn, 2, 11);

                submitBtn.setOnAction(e -> {

                });

                return pane;
            }

            public void quitWarning() {
                //create the pop up warning for quiting the program
                warningStage.initModality(Modality.WINDOW_MODAL);
                Button yesBtn = new Button("  Yes  ");
                Button noBtn = new Button("  No  ");
                GridPane pane = new GridPane();
                pane.setVgap(5.5);
                pane.setHgap(10);
                pane.setPadding(new Insets(12, 20, 12, 20));
                pane.setAlignment(Pos.CENTER);
                pane.add(new Text("Are you sure?"), 1, 0);
                pane.add(yesBtn, 0, 1);
                pane.add(noBtn, 2, 1);
                yesBtn.setOnAction(e -> {
                    Platform.exit();
                });
                noBtn.setOnAction(e -> {
                    warningStage.close();
                });

                //create a scene
                Scene scene = new Scene(pane, 400, 300);
                warningStage.setScene(scene);
                warningStage.show();
            }

        }
        
        //full time, part time and non credit students
        class Registration {
            //set the stage
            Stage stage = new Stage();
            private double total;
            private final double fee = 5.0;

            //create an admissions and student object
            Admissions demo = new Admissions();
            Student stud = new Student();

            //create labels for panes
            Label ssnLb = new Label("SSN: ");
            Label courseLb = new Label("Course ID: ");
            Label totalCreditsLb = new Label("Total Credits: ");

            //create the table view
            TableView<Student> table;   
            
            //JDBC driver name and database URL
            static final String Driver = "com.sql.jdbc.Driver";
            static final String DATABSAE_URL = "jdbc:mysql://localhost/Boola";

            //launch the application
            Connection connection;
            Statement statement = null; // query statement
            ResultSet result = null;
            
            
            
            class fullTime{
                
                //get student information
                String firstname = stud.getFirstName();
                String MI = stud.getMI();
                String lastName = stud.getLastName();
                
                
            }
            
            class partTime{
                
            }
            
            class nonCredit{
                
            }

        }
        
        //recievables and class schedules
        class Repost{
            
        
        }
        

        //create the scene
        Scene scene = new Scene(pane, 800, 500);
        Admissions admi = new Admissions();

        //set action for matriculated menu item 
        matriculated.setOnAction(e
                -> {
            pane.setCenter(admi.MatriculatedPane());
        }
        );
        //set action for non matriculated menu item
        nonMatriculated.setOnAction(e
                -> {
            pane.setCenter(admi.nonMatriculatedPane());
        }
        );
        //set action for quit menu option
        quit.setOnAction(e
                -> {
            admi.quitWarning();
        }
        );

        //set the stage
        primaryStage.setTitle(
                "Boola Boola University");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
