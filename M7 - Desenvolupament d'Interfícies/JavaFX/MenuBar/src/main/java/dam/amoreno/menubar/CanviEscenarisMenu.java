package dam.amoreno.menubar;

import javafx.application.Application;

import javafx.event.ActionEvent;

import javafx.stage.Stage;



import javafx.scene.control.MenuItem;

import javafx.scene.control.Menu;

import javafx.scene.control.MenuBar;



import javafx.scene.Scene;

import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;





public class CanviEscenarisMenu extends Application {



    public static void main(String[] args) {



        launch(args);



    }



    @Override



    public void start(Stage teatre) throws Exception{


//Declaració controls Escenari1



        MenuItem menu12 = new MenuItem("Menu 2");

        MenuItem menu13 = new MenuItem("Menu 3");



        Menu menu1 = new Menu("Opcions");

        menu1.getItems().add(menu12);

        menu1.getItems().add(menu13);



        MenuBar menu10=new MenuBar();

        menu10.getMenus().add(menu1);



        Pane decorat1=new Pane(menu10);

        decorat1.setStyle("-fx-background-color: #009900;");



        Scene escenari1=new Scene(decorat1, 1000, 500);



//Declaració controls Escenari 2



        MenuItem menu21 = new MenuItem("Menu 1");

        MenuItem menu23 = new MenuItem("Menu 3");



        Menu menu2 = new Menu("Opcions");

        Menu menu22 = new Menu("Opcions2");



        menu2.getItems().add(menu21);

        menu2.getItems().add(menu23);



        MenuBar menu20=new MenuBar();

        menu20.getMenus().add(menu2);

        menu20.getMenus().add(menu22);



        Pane decorat2=new Pane(menu20);

        decorat2.setStyle("-fx-background-color: #990000;");



        Scene escenari2=new Scene(decorat2, 500, 100);



//-------------------------------------------------------



        teatre.setTitle("Teatre-Escenari1");



        teatre.setScene(escenari1);



        teatre.show();



        menu21.setOnAction((ActionEvent e)->{

            teatre.setScene(escenari1);

        });



        menu12.setOnAction((ActionEvent e)->{

            teatre.setScene(escenari2);

        });

    }

}


