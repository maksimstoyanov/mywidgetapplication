/*
 * Programmer: Stoyanov, Maksim
 * Chemeketa Community College
 * June 1, 2017
 * Class: CIS233J
 * Assignment: Widget Application (part 1)
 * File Name: WidgetApp.java
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


/**
 *
 * @author <a href= "mailto:mstoyano@my.chemeketa.edu" >Maksim</a>
 */
public class WidgetApp extends Application
{
    //keeps track of progress bar(value)
    float progress;
    
    //temp color for colorSelector
    Color tempColor;
    
    //Default fontSize before change and after clear
    int fontSizeGlobal = 7;
    
    //keeps track if root is bold or not
    boolean isBold = false;
    
    //time and date stamp for final file output
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
    String formattedDate = sdf.format(date);
    
    //final file output string
    String finalOutput = "Survey entry: " + formattedDate + "\r\n\n";
    
    @Override
    public void start(Stage primaryStage)
    {
        //main view
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 480, 400, Color.WHITESMOKE);
        
        //stackpane bottom will hold first tab's contents
        StackPane bottom = new StackPane();
        bottom.setPadding(new Insets(5, 5, 5, 5));
        
        //stackpane bottom2 will hold second tab's contents
        StackPane bottom2 = new StackPane();
        bottom.setPadding(new Insets(5, 5, 5, 5));
        
        //stackpane bottom3 will hold third tab's contents
        StackPane bottom3 = new StackPane();
        bottom.setPadding(new Insets(5, 5, 5, 5));
        
        //stackpane bottom3 will hold fourth tab's contents
        StackPane bottom4 = new StackPane();
        bottom.setPadding(new Insets(5, 5, 5, 5));
           
        //HBoX TOP
        VBox tipTop = new VBox();
        
        //calling the menu return
        menu(root, primaryStage, tipTop);
        
        //toolBox call
        tools(root, primaryStage, tipTop);
        
        //calling tabs
        tabs(root, primaryStage, bottom, bottom2, bottom3, bottom4, tipTop);
        
        //set top hbox
        root.setTop(tipTop);
        
        primaryStage.setTitle("Your Average Survey");
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //non-resizable because i'm using x and y coordinates.
        primaryStage.setResizable(false);
    }

    
    /**
     * Creates a menu bar on the TOP of root
     * @param root
     * @param primaryStage
     * @return
     */
    public VBox menu(BorderPane root, Stage primaryStage, VBox tipTop)
    {
        /** Initial MenuBar SetUp ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** *
        ** ** ** ** ** ** ** ** ** ** **/
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);
        /** End MenuBar Setup ** ** ** ** **
        ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
        
        /** Menu setup! Contains File, and Edit, perhaps About ** ** ** ** *
        ** ** ** ** ** ** ** ** ** ** **/
        //file
        Menu fileMenu = new Menu("File");
        MenuItem newFileI = new MenuItem("New");
        MenuItem newSaveAsI = new MenuItem("Save As");
        MenuItem newOpenI = new MenuItem("Open");
        MenuItem newExitI = new MenuItem("Kill 💀");
        
        fileMenu.getItems().addAll(newFileI, newSaveAsI, newOpenI, 
                new SeparatorMenuItem(), newExitI);
        
        //edit
        Menu editMenu = new Menu("Edit");
        MenuItem newClearI = new MenuItem("Clear");
        MenuItem newRestartI = new MenuItem("Restart");
        MenuItem newAbortI = new MenuItem("Abort Tab");
        MenuItem newAbortExitI = new MenuItem("Abort and Output");
        
        editMenu.getItems().addAll(newClearI, newRestartI, new SeparatorMenuItem(),
                newAbortI, newAbortExitI);
        
        //help
        Menu helpMenu = new Menu("Help");
        MenuItem aboutI = new MenuItem("About");
        aboutI.setOnAction((ActionEvent e) ->
        {
            Alert about = new Alert(AlertType.INFORMATION);
                about.setTitle("About");
                about.setHeaderText("mstoyanov@mychemeketa.edu");
                about.setContentText("Please contact me if you have any further questions...");
                about.showAndWait();
        });
        
        helpMenu.getItems().addAll(aboutI);
        
        
        /** End Menu Setup ** ** ** ** **
        ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
        
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
        tipTop.getChildren().add(menuBar);
        return tipTop;
    }
    
    
    /**
     * This will change the color of the root's background after being called
     * @param root
     * @param primaryStage
     * @param color
     * @return
     */
    public BorderPane rootColor(BorderPane root, Stage primaryStage, String color)
    {
        root.setStyle("-fx-background-color: " + color);
                
        return root;
    }
  

    
    /**
     * Creates the 4 tabs on top of root and calls each tabs' content method.
     * @param root
     * @param primaryStage
     * @param bottom
     * @param bottom2
     * @param bottom3
     * @param bottom4
     * @return
     */
    public TabPane tabs(BorderPane root, Stage primaryStage, StackPane bottom,
            StackPane bottom2, StackPane bottom3, StackPane bottom4, VBox tipTop)
    {
        /** ** ** Tab Setup ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** *
        ** ** ** ** ** ** ** ** ** ** **/
        TabPane tabs = new TabPane();
        Tab tOne = new Tab("Part 1");
        tOne.setTooltip(new Tooltip("You will not be able to skip to the next\n"
                + "tab until you've answered every question."));
        tOne.setClosable(false);
        
        Tab tTwo = new Tab("Part 2");
        tTwo.setTooltip(new Tooltip("You will not be able to skip to the next\n"
                + "tab until you've answered every question."));
        tTwo.setClosable(false);
        
        Tab tThree = new Tab("Part 3");
        tThree.setTooltip(new Tooltip("You will not be able to skip to the next\n"
                + "tab until you've answered every question."));
        tThree.setClosable(false);
        
        Tab tFour = new Tab("Part 4");
        tFour.setTooltip(new Tooltip("After finishing the last question, you will\n"
                + "be able to print the questions you've answered on to a\n"
                + "text file which can then be viewed and kept for memories."));
        tFour.setClosable(false);
        
        //calling each tabs' methods
        //also applying tOne - tFour because I had to use the "enable disable tabs" method
        tOne.setContent(firstTab(root, primaryStage, bottom, tabs, tOne,
                tTwo, tThree, tFour, tipTop));
        
        tTwo.setContent(secondTab(root, primaryStage, bottom2, tabs, tOne,
                tTwo, tThree, tFour, tipTop));
        
        tThree.setContent(thirdTab(root, primaryStage, bottom3, tabs, tOne,
                tTwo, tThree, tFour, tipTop));
        
        tFour.setContent(fourthTab(root, primaryStage, bottom4, tabs, tOne,
                tTwo, tThree, tFour, tipTop));
        
        
        tabs.getTabs().addAll(tOne, tTwo, tThree, tFour);
        
        enableDisableTabs(tabs, tOne, tTwo, tThree, tFour);
        
        root.setCenter(tabs);
        /** End Tab Setup ** ** ** ** **
        ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
        return tabs;
    }
    
    
    /**
     * First tab, this is all the contents in the first tab.
     * @param root
     * @param primaryStage
     * @param bottom
     * @param tabs
     * @param tOne
     * @param tTwo
     * @param tThree
     * @param tFour
     * @param tipTop
     * @return
     */
    public StackPane firstTab(BorderPane root, Stage primaryStage, StackPane bottom, 
            TabPane tabs, Tab tOne, Tab tTwo, Tab tThree, Tab tFour, VBox tipTop)
    {   
        //progress bar for the completion of the servey
        ProgressBar pb = new ProgressBar(progress);
        
        pb.setTranslateY(136);
          
        //progress indicator
        ProgressIndicator pi = new ProgressIndicator();
        pi.setTranslateX(70);
        pi.setTranslateY(138);
        pi.setMaxSize(30, 30);
        
        //separator!! For neatness
        Separator sep1 = new Separator();
        sep1.setOrientation(Orientation.HORIZONTAL);
        sep1.setTranslateY(-95);

        Separator sep2 = new Separator();
        sep2.setOrientation(Orientation.HORIZONTAL);
        sep2.setTranslateY(-30);

        Separator sep3 = new Separator();
        sep3.setOrientation(Orientation.HORIZONTAL);
        sep3.setTranslateY(120);
        
        Separator sep4 = new Separator();
        sep4.setOrientation(Orientation.HORIZONTAL);
        sep4.setTranslateY(40);
        //end seporator!
        
        
        //navagational buttons
        Button bNext = new Button ("Next Tab");
        bNext.setDisable(true);
        bNext.setTooltip(new Tooltip("This button will take you to the next tab."));
        bNext.setTranslateX(180);
        bNext.setTranslateY(134);
        bNext.setOnAction((ActionEvent e) ->
        {
            progress = .3f;
            tabs.getSelectionModel().selectNext();
            enableDisableTabs(tabs, tOne, tTwo, tThree, tFour);
            
            pb.setProgress(0.3f);
            pi.setProgress(0.3f);
        });

        // end of navagational buttons
        
        //Servey Questions
        //Introduction Label
        Label lblIntro = new Label("Hello Human! Today we are going to go "
                + "through some simple questions! "
                + "I hope you're ready to answer them with "
                + "what ever honesty you've got! Have fun.");
        lblIntro.setWrapText(true);
        lblIntro.setTranslateX(10);
        lblIntro.setTranslateY(-130);
        
        
        //First Question Label, and Text Field
        Label lblName = new Label("What's your name?");
        lblName.setTranslateX(-164);
        lblName.setTranslateY(-65);
        
        TextField bName = new TextField();
        bName.setPromptText("First Name");
        bName.setOnAction((ActionEvent e) ->
        {
            System.out.println(bName.getText());
            System.out.println(progress);
            progress = progress +.1f;
            pb.setProgress(progress);
            pi.setProgress(progress);
            
            checkTab1Progress(bNext);
            toFile("Your name: " + bName.getText() + "\r\n");
        });
        bName.setMaxWidth(100);
        bName.setTranslateX(-50);
        bName.setTranslateY(-65);
        //End first question (Name question, Label & textFiled)
        
        
        //Second Question
        Label lblPassword = new Label("Enter a password which you \n"
                + "believe nobody can guess.");
        lblPassword.setTranslateX(-140);
        lblPassword.setTranslateY(0);
        
        PasswordField fieldPass = new PasswordField ();
        fieldPass.setPromptText("Password");
        fieldPass.setOnAction((ActionEvent e) ->
        {
            System.out.println(progress);
            progress = progress +.1f;
            pb.setProgress(progress);
            pi.setProgress(progress);
            
            checkTab1Progress(bNext);
            toFile("Your password is : ****** \r\n");
        });
        
        fieldPass.setMaxWidth(100);
        fieldPass.setTranslateX(-10);
        fieldPass.setTranslateY(0);
        //End second Question
        
        //Third Question, ColorPicker and Label
        Label lblColorChoice = new Label("What's your favorite color?");
        lblColorChoice.setTranslateX(-145);
        lblColorChoice.setTranslateY(80);
        
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setTooltip(new Tooltip("Opens a color panel for selection"));
        colorPicker.setValue(Color.WHITE);
        colorPicker.setOnAction((ActionEvent) ->
        {
            System.out.println(progress);
            progress = progress +.1f;
            pb.setProgress(progress);
            pi.setProgress(progress);
            
            checkTab1Progress(bNext);
            toFile("Your favorite color in Hex is : " + colorPicker.getValue() + "\r\n");
        });
        colorPicker.setTranslateX(0);
        colorPicker.setTranslateY(80);
        //End Third question (Color question, ColorChooser & Label)
        
        root.setBottom(bottom);
        bottom.getChildren().addAll(lblIntro, lblName, bName, 
                lblPassword, fieldPass, colorPicker, lblColorChoice,
                bNext, sep1, sep2, sep3, sep4, pb, pi);
        
        return bottom;
    }
    
    public void checkTab1Progress(Button b)
    {
        if (progress >= .3f)
        {
            b.setDisable(false);
        }
    }

    public ToolBar tools(BorderPane root, Stage primaryStage, VBox tipTop)
    {
        ToolBar tools = new ToolBar();
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.getStyleClass().add("color-pickerT");
        colorPicker.setMaxWidth(50);
        
        colorPicker.setTooltip(new Tooltip("Change the background color"));
        colorPicker.setValue(tempColor);
        
        colorPicker.setOnAction(new EventHandler()
        {
            @Override
            public void handle(Event event)
            {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Note");
                alert.setHeaderText("Note: You are about to change the color of your survey!");
                alert.setContentText("Press cancel if you wish to abort this action!");
                
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)
                {
                    // Ok
                    Color c = colorPicker.getValue();
                    tempColor = colorPicker.getValue();

                        String rgb = "rgb(" + c.getRed()*255 + ", " + c.getGreen()*255 +
                                ", " + c.getBlue()*255 + ",);";

                        rootColor(root, primaryStage, rgb);
                        
                }   else 
                    {
                        // Cancel
                        Alert alert2 = new Alert(AlertType.INFORMATION);
                        alert2.setTitle("Info");
                        alert2.setHeaderText("Survey Color Status:");
                        alert2.setContentText("The color of your survey will remain unchanged.");
                        alert2.showAndWait();
                        
                        colorPicker.setValue(tempColor);
                    }
            }
        });
        
        Button bold = new Button();
        bold.setOnAction((ActionEvent e) ->
        {
            if (isBold == false)
            {
                root.setStyle("-fx-font-weight: bold;");
                isBold = true;
            } else{
                root.setStyle("-fx-font-weight: normal;");
                isBold = false;
            }
            
        });
        
        Image iBold = new Image("bold.png");
        bold.setGraphic(new ImageView(iBold));
        
        tools.getItems().addAll(colorPicker, bold);
        
        tipTop.getChildren().add(tools);
        return tools;
    }
    
    
    /**
    * All the contents of the second tab.
    * Again, tab1 - tab4 were added because I couldn't find a way to call the 
    * "enableDisableTabs() method.
    * @param root
    * @param primaryStage
    * @param bottom2
    * @param tabs
    * @param tOne
    * @param tTwo
    * @param tThree
    * @param tFour
    * @return
    */
    public StackPane secondTab(BorderPane root, Stage primaryStage, StackPane bottom2, 
            TabPane tabs, Tab tOne, Tab tTwo, Tab tThree, Tab tFour, VBox tipTop)
    {   
        //progress bar for the completion of the servey
        ProgressBar pb = new ProgressBar(progress);
        pb.setTranslateY(136);
        pb.setProgress(.3f);
        
          
        //progress indicator
        ProgressIndicator pi = new ProgressIndicator();
        pi.setProgress(.3f);
        pi.setTranslateX(70);
        pi.setTranslateY(138);
        pi.setMaxSize(30, 30);
        
        
        //seporators
        Separator sep1 = new Separator();
        sep1.setOrientation(Orientation.HORIZONTAL);
        sep1.setTranslateY(120);
        
        Separator sep2 = new Separator();
        sep2.setOrientation(Orientation.HORIZONTAL);
        sep2.setTranslateY(-130);
        
        Separator sep3 = new Separator();
        sep3.setOrientation(Orientation.HORIZONTAL);
        sep3.setTranslateY(-25);
        
        Separator sep4 = new Separator();
        sep4.setOrientation(Orientation.HORIZONTAL);
        sep4.setTranslateY(30);
        //end seporators
        
        //navagational buttons
        Button bNext = new Button ("Next Tab");
        bNext.setDisable(true);
        bNext.setTooltip(new Tooltip("This button will take you to the next tab."));
        bNext.setTranslateX(180);
        bNext.setTranslateY(134);
        bNext.setOnAction((ActionEvent e) ->
        {
            progress = .5f;
            tabs.getSelectionModel().selectNext();
            enableDisableTabs(tabs, tOne, tTwo, tThree, tFour);
            pb.setProgress(.5f);
            pi.setProgress(.5f);
            
        });
        
        Button bLast = new Button("Last Tab");
        bLast.setTooltip(new Tooltip("This button will take you to the last tab."));
        bLast.setTranslateX(-180);
        bLast.setTranslateY(134);
        bLast.setOnAction((ActionEvent e) ->
        {
            tabs.getSelectionModel().selectPrevious();
            enableDisableTabs(tabs, tOne, tTwo, tThree, tFour);
        });
        // end of navagational buttons
        
        
        //Label for question 1
        Label question1 = new Label("What are you scared of?");
        question1.setTranslateY(-140);
        
        //Check boxes and VBox
        VBox vbChecks = new VBox();
        vbChecks.setTranslateY(0);
        vbChecks.setMaxSize(400, 200);
        vbChecks.setTranslateX(-25);
        vbChecks.setTranslateY(-25);
        
        CheckBox check1 = new CheckBox();
        check1.setText("The Dark");
        check1.setTextAlignment(TextAlignment.LEFT);
        check1.setTranslateX(10);
        check1.setTranslateY(0);
        check1.setWrapText(true);
        check1.setPadding(new Insets(5, 5, 12, 5));
        
        CheckBox check2 = new CheckBox("Spiders");
        check2.setTranslateX(10);
        check2.setTranslateY(0);
        check2.setWrapText(true);
        check2.setPadding(new Insets(5, 5, 12, 5));
        
        CheckBox check3 = new CheckBox("Being Bitten By a Dark Spider");
        check3.setTranslateX(10);
        check3.setTranslateY(0);
        check3.setWrapText(true);
        check3.setPadding(new Insets(5, 5, 12, 5));
        // end of check box and vbox for checks
        
        //if statment which tests if one/+ of the checks were touched
        check1.setOnAction((e)-> 
        {
            progress = progress + .1f;
            pb.setProgress(progress);
            pi.setProgress(progress);
            
            checkTab2Progress(bNext, bLast);
            toFile("You're scared of : " + check1.getText()+ ".\r\n");
        });
        
        check2.setOnAction((e)-> 
        {
            progress = progress + .1f;
            pb.setProgress(progress);
            pi.setProgress(progress);
            
            checkTab2Progress(bNext, bLast);
            toFile("You're scared of : " + check2.getText() + ".\r\n");
        });
        
        check3.setOnAction((e)-> 
        {
            progress = progress + .1f;
            pb.setProgress(progress);
            pi.setProgress(progress);
            
            checkTab2Progress(bNext, bLast);
            toFile("You're scared of : " + check3.getText() + ".\r\n");
        });
       
        
        //VBox for Radio butts & a GroupToggle
        VBox vbRadio = new VBox();
        vbRadio.setMaxSize(200, 65);
        vbRadio.setPadding(new Insets(5, 5, 12, 5));
        vbRadio.setTranslateX(-115);
        vbRadio.setTranslateY(85);
        //end vbRadio
        
        //label for question 2
        Label question2 = new Label("Where would you prefer living in?");
        question2.setTranslateY(20);
        
        
        ToggleGroup group = new ToggleGroup();
        RadioButton opt1 = new RadioButton("City");
        opt1.setToggleGroup(group);
        opt1.setTranslateY(-20);
        
        RadioButton opt2 = new RadioButton("Suburbs");
        opt2.setToggleGroup(group);
        opt2.setTranslateY(-15);
        
        RadioButton opt3 = new RadioButton("Country");
        opt3.setToggleGroup(group);
        opt3.setTranslateY(-10);
        
        opt1.setOnAction((e)-> 
        {
            progress = progress + .1f;
            pb.setProgress(progress);
            pi.setProgress(progress);
            
            checkTab2Progress(bNext, bLast);
            toFile("You would rather prefer the : " + opt1.getText() + ".\r\n");
        });
        
        opt2.setOnAction((e)-> 
        {
            progress = progress + .1f;
            pb.setProgress(progress);
            pi.setProgress(progress);
            
            checkTab2Progress(bNext, bLast);
            toFile("You would rather prefer the : " + opt2.getText() + ".\r\n");
        });
        
        opt3.setOnAction((e)-> 
        {
            progress = progress + .1f;
            pb.setProgress(progress);
            pi.setProgress(progress);
            
            checkTab2Progress(bNext, bLast);
            toFile("You would rather prefer the : " + opt3.getText() + ".\r\n");
        });
        
        
        //end radio butts & group
        
        vbRadio.getChildren().addAll(opt1, opt2, opt3);
        
        vbChecks.getChildren().addAll(check1, check2, check3);
        
        bottom2.getChildren().addAll(question1, question2, bLast, bNext, vbChecks, 
                vbRadio, sep1, sep2, sep3, sep4, pb, pi);
        root.setBottom(bottom2);
        
        return bottom2;
    }

    public void checkTab2Progress(Button bN, Button bL)
    {
        if(progress >= .3f)
            {
                bL.setDisable(true);
            }
            
            if(progress >= .5f)
            {
                bN.setDisable(false);
            }
    }   
    
    
    
    /**
     * Third tab's contents!
     * @param root
     * @param primaryStage
     * @param bottom3
     * @param tabs
     * @param tOne
     * @param tTwo
     * @param tThree
     * @param tFour
     * @return
     */
    public StackPane thirdTab(BorderPane root, Stage primaryStage, StackPane bottom3, 
        TabPane tabs, Tab tOne, Tab tTwo, Tab tThree, Tab tFour, VBox tipTop)
    {   
       
        
        //        imageLink = "clear.png";
        //        cls.setGraphic(new ImageView(image));
        //Tab Area 51 for clearing the whole tab.
        Button cls = new Button("tab 3");
        
        
        //progress bar for the completion of the servey
        ProgressBar pb = new ProgressBar(progress);
        pb.setTranslateY(136);
        pb.setProgress(.5f);
        
          
        //progress indicator
        ProgressIndicator pi = new ProgressIndicator();
        pi.setProgress(.5f);
        pi.setTranslateX(70);
        pi.setTranslateY(138);
        pi.setMaxSize(30, 30);
        
        //seporators
        Separator sep3 = new Separator();
        sep3.setOrientation(Orientation.HORIZONTAL);
        sep3.setTranslateY(120);
        //end seporators
        
        //navagational buttons
        Button bNext = new Button ("Next Tab");
        bNext.setDisable(true);
        bNext.setTooltip(new Tooltip("This button will take you to the next tab."));
        bNext.setTranslateX(180);
        bNext.setTranslateY(134);
        bNext.setOnAction((ActionEvent e) ->
        {
            progress = .7f;
            tabs.getSelectionModel().selectNext();
            enableDisableTabs(tabs, tOne, tTwo, tThree, tFour);
            pb.setProgress(.5f);
            pi.setProgress(.5f);
        });
        
        Button bLast = new Button("Last Tab");
        bLast.setTooltip(new Tooltip("This button will take you to the last tab."));
        bLast.setTranslateX(-180);
        bLast.setTranslateY(134);
        bLast.setOnAction((ActionEvent e) ->
        {
            tabs.getSelectionModel().selectPrevious();
            enableDisableTabs(tabs, tOne, tTwo, tThree, tFour);
        });
        // end of navagational buttons
        
        
        //begining of Label and Text Area question 2
        Label question2 = new Label("Please explain why in as many words as you desire.");
        question2.setTranslateX(-65);
        question2.setTranslateY(-90);
        
        //text area for question 2
        TextArea answer2 = new TextArea();
        answer2.setStyle("-fx-font-size: 7;");
        answer2.setWrapText(true);
        answer2.setPromptText("I chose this because. . .");
        answer2.setMaxSize(300, 100);
        answer2.setTranslateX(-65);
        answer2.setTranslateY(-10);
        answer2.setOnKeyPressed(new EventHandler<KeyEvent>() 
        {
        @Override
            public void handle(KeyEvent keyEvent) 
            {
                if (keyEvent.getCode() == KeyCode.ENTER)  
                {
                    progress = progress +.1f;
                    pb.setProgress(progress);
                    pi.setProgress(progress);
                    
                    checkTab2Progress(bNext, bLast);
                    toFile("Your reason for choosing the operating system: " + answer2.getText() + "\r\n");
                }
            }  
        });   
            
        // end of question 2

        //begining of Label for question 1 and choice box
        Label question1 = new Label("What's your everyday prefered operating system?");
        question1.setTranslateX(-85);
        question1.setTranslateY(-130);
        
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList
            ("Windows", "Mac OSX", "Linux"));
        cb.setOnAction((Event e) ->
        {
            answer2.setPromptText("I chose " + cb.getValue() + "  because. . .");
            progress = progress +.1f;
            pb.setProgress(progress);
            pi.setProgress(progress);
            
            checkTab2Progress(bNext, bLast);
            toFile("You picked : " + cb.getValue() + " as your primary Operating System.\r\n");
        });
        cb.setTranslateX(100);
        cb.setTranslateY(-130);
        //end of label Question 1 and choice box
        
        
        //start of slider for font
        Slider fontSize = new Slider();
        fontSize.setTooltip(new Tooltip("This will change the size of the text under the slider."));
        fontSize.setMaxWidth(220);
        fontSize.setTranslateX(-35);
        fontSize.setTranslateY(-70);
        fontSize.setMin(0);
        fontSize.setMax(24);
        fontSize.setValue(7);
        
        Label lFontSize = new Label();
        lFontSize.setText("Font Size: " + fontSizeGlobal);
        lFontSize.setTranslateX(-180);
                lFontSize.setTranslateY(-72);
        
        fontSize.valueProperty().addListener(new ChangeListener<Number>() 
        {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) 
            {
                answer2.setStyle("-fx-font-size: " + fontSize.getValue() + "pt;");
                    
                fontSizeGlobal = (int) fontSize.getValue();
                    
                //label for changing font
                lFontSize.setText("Font Size: " + fontSizeGlobal);
            }
        });
        
        cls.setOnAction((ActionEvent e) ->
        {
            System.out.println("tab 3");
            fontSize.setValue(7.0);
            cb.setValue(null);
            answer2.setText(null);
            answer2.setPromptText("I chose this because. . .");
            
            bNext.setDisable(true);
            bLast.setDisable(false);
        });
        
        
        
        root.setBottom(bottom3);
        bottom3.getChildren().addAll(cb, question1, question2, answer2, fontSize, 
                lFontSize, sep3, bLast, bNext, pb, pi);
        
        return bottom3;
    }
    
    public void checkTab3Progress(Button bN, Button bL)
    {
        if(progress >= .51f)
            {
                bL.setDisable(true);
            }
            
            if(progress >= .5f)
            {
                bN.setDisable(false);
            }
    } 

    
    /**
     * Fourth tab's contents! 
     * @param root
     * @param primaryStage
     * @param bottom4
     * @param tabs
     * @param tOne
     * @param tTwo
     * @param tThree
     * @param tFour
     * @return
     */
    public StackPane fourthTab(BorderPane root, Stage primaryStage, StackPane bottom4, 
        TabPane tabs, Tab tOne, Tab tTwo, Tab tThree, Tab tFour, VBox tipTop)
    {   
        StackPane tab4c = new StackPane();
        
        Button cls = new Button("tab 4");
        cls.setOnAction((ActionEvent e) ->
        {
            System.out.println("fourth tab test");
        });
         
        //progress bar for the completion of the servey
        ProgressBar pb = new ProgressBar(progress);
        pb.setTranslateY(136);
        pb.setProgress(.7f);
        
          
        //progress indicator
        ProgressIndicator pi = new ProgressIndicator();
        pi.setProgress(.7f);
        pi.setTranslateX(70);
        pi.setTranslateY(138);
        pi.setMaxSize(30, 30);
        
        //seporators
        Separator sep3 = new Separator();
        sep3.setOrientation(Orientation.HORIZONTAL);
        sep3.setTranslateY(120);
        //end seporators
        
        //navagational button
        Button bLast = new Button("Last Tab");
        bLast.setTooltip(new Tooltip("This button will take you to the last tab."));
        bLast.setTranslateX(-180);
        bLast.setTranslateY(134);
        bLast.setOnAction((ActionEvent e) ->
        {
            tabs.getSelectionModel().selectPrevious();
            enableDisableTabs(tabs, tOne, tTwo, tThree, tFour);
        });
        // end of navagational button
        
        //final button to print the answers to a text file
        Button bPrint = new Button();
        bPrint.setDisable(true);
        Image print = new Image("print.png");
        bPrint.setGraphic(new ImageView(print));
        bPrint.setTranslateX(250);
        bPrint.setTranslateY(320);
        bPrint.setMaxWidth(100);
        
        
        //question 1
        Label quest1 = new Label("How old will you be in 5 years?");
        quest1.setTranslateX(20);
        quest1.setTranslateY(-20);
        
        
        //answer 1
        TextField ans1 = new TextField();
        ans1.setOnAction((ActionEvent e) ->
        {
            progress = progress +.1f;
            pb.setProgress(progress);
            pi.setProgress(progress);
            
            checkTab4Progress(bLast, bPrint);
            toFile("You will be " + ans1.getText() + " years old in 5 years.\r\n");
        });
        ans1.setPromptText("8");
        ans1.setTranslateX(140);
        ans1.setTranslateY(-20);
        ans1.setMaxWidth(45);
        
        //question 2
        Label quest2 = new Label("What country were you born in?");
        quest2.setTranslateX(20);
        quest2.setTranslateY(20);
        
        
        //answer 2
        TextField ans2 = new TextField();
        ans2.setOnAction((ActionEvent e) ->
        {
            progress = progress +.1f;
            pb.setProgress(progress);
            pi.setProgress(progress);
            
            checkTab4Progress(bLast, bPrint);
            toFile("Your place of birth is " + ans2.getText() + "\r\n");
        });
        ans2.setPromptText("United States of America");
        ans2.setTranslateX(190);
        ans2.setTranslateY(20);
        ans2.setMaxWidth(150);
        
        //question 3
        Label quest3 = new Label("What was the last thing you ate?");
        quest3.setTranslateX(20);
        quest3.setTranslateY(60);
        
        
        //answer 3
        TextField ans3 = new TextField();
        ans3.setOnAction((e) ->
        {
            progress = progress +.1f;
            pb.setProgress(progress);
            pi.setProgress(progress);
            
            checkTab4Progress(bLast, bPrint);
            toFile("The last thing you ate was: " + ans3.getText() + ". \r\n");
        });
        ans3.setPromptText("Paper clip");
        ans3.setTranslateX(170);
        ans3.setTranslateY(60);
        ans3.setMaxWidth(100);
        
        
        
        //label to scroll
        Label lScroll = new Label("Please scroll down to view the last section.");
        lScroll.setDisable(true);
        lScroll.setTranslateX(120);
        lScroll.setTranslateY(200);
        lScroll.setMaxWidth(225);
        
        
        
        
        //label to finish
        Label lPrint = new Label("Press Clippy to save your answers to a file.");
        lPrint.setTranslateX(40);
        lPrint.setTranslateY(320);
        lPrint.setMaxWidth(225);
        
        //start scroll bar
        ScrollPane bar = new ScrollPane(tab4c);
        bar.setHbarPolicy(ScrollBarPolicy.NEVER);
        bar.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        bar.setVmax(100);
        bar.setVmin(0);
        bar.setVvalue(0);
        
        bar.vvalueProperty().addListener((ObservableValue<? extends Number> ov, 
        Number old_val, Number new_val) -> 
        {
            quest1.setTranslateY(-20 - (new_val.intValue())*2);
            ans1.setTranslateY(-20 - (new_val.intValue())*2);
            
            quest2.setTranslateY(20 - (new_val.intValue())*2);
            ans2.setTranslateY(20 - (new_val.intValue())*2);
            
            quest3.setTranslateY(60 - (new_val.intValue())*2);
            ans3.setTranslateY(60 - (new_val.intValue())*2);
            
            lPrint.setTranslateY(320 - (new_val.intValue())*2);
            bPrint.setTranslateY(320 - (new_val.intValue())*2);
        });
        
        //print action
        bPrint.setOnAction((ActionEvent e) -> 
        {
            outPut(finalOutput);
        });
        
        
        
        
        tab4c.getChildren().addAll(quest1, ans1, quest2, ans2, quest3, ans3,
                                    lPrint, bPrint, lScroll);
        bar.setContent(tab4c);
        bar.setMaxHeight(282);
        bar.setTranslateY(-21);
        
        bottom4.getChildren().addAll(bar, bLast, sep3, pb, pi);
        
        
        return bottom4;
    }
   
    
    public void checkTab4Progress(Button b, Button p)
    {
        if (progress >= .71f)
        {
            b.setDisable(true);
        }
        
        if (progress >= 1.0f)
        {
            p.setDisable(false);
        }
    }    
    
    
    /**
     * This will find the tab that's in use and disable every other tab around it
     * @param tabs
     * @param tOne
     * @param tTwo
     * @param tThree
     * @param tFour
     * @return
     */
    public void enableDisableTabs(TabPane tabs, Tab tOne, Tab tTwo, 
            Tab tThree, Tab tFour)
    {
        if (tOne.isSelected())
        {
            tOne.setDisable(false);
            tTwo.setDisable(true);
            tThree.setDisable(true);
            tFour.setDisable(true);
        } else if (tTwo.isSelected())
          {
              tTwo.setDisable(false);
              tOne.setDisable(true);
              tThree.setDisable(true);
              tFour.setDisable(true);
          } else if (tThree.isSelected())
            {
                tThree.setDisable(false);
                tOne.setDisable(true);
                tTwo.setDisable(true);
                tFour.setDisable(true);
            } else if (tFour.isSelected())
              {
                  tFour.setDisable(false);
                  tOne.setDisable(true);
                  tThree.setDisable(true);
                  tTwo.setDisable(true);
              }
    }
    
    public String toFile(String s)
    {
        finalOutput = finalOutput + s;
        
        System.out.println(finalOutput);
        
        return finalOutput;
    }
    
    public void outPut(String x)
    {
        try
        {
            // Create file 
            FileWriter file = new FileWriter("SurveyResults.txt");
            BufferedWriter SurveyResults = new BufferedWriter(file);
            SurveyResults.write(finalOutput);
            
            //Close the output stream
            SurveyResults.close();
        }   catch (Exception e)
                {
                    //Catch exception if any
                    System.err.println("Error: " + e.getMessage());
                } 
    }
    
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
}