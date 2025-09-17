package com.scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Table extends Application {
    public char nextChar = 'A';
    public Random randomizer = new Random();
    public int sum;
    public int quantum;
    public int arrLeft = 32;
    public int vals[];
    public int currentIter = 1;
    List<Process> processes = new ArrayList<>();
    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage priStage) throws Exception {
        priStage.setTitle("Experiments");

        TableView<Process> table = new TableView<>();
        table.setEditable(true);
        TableViewSelectionModel<Process> sModel = table.getSelectionModel();
        sModel.setSelectionMode(SelectionMode.MULTIPLE);
        TableColumn<Process, String> pID = new TableColumn<>("Process ID");
        TableColumn<Process, String> priority = new TableColumn<>("Priority");
        TableColumn<Process, String> arrival = new TableColumn<>("Arrival");
        TableColumn<Process, String> burstTime = new TableColumn<>("Burst Time");
        pID.setCellValueFactory(new PropertyValueFactory<>("processID"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        arrival.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        burstTime.setCellValueFactory(new PropertyValueFactory<>("burstTime"));

        table.getColumns().addAll(pID,priority,arrival,burstTime);
        generateNumbers(5,32);
        //create a copy
        int[] burstVals = vals;
        generateNumbers(5, 33);

        //Test multi object support
        for (int i = 0; i < 5; i++){
            // table.getItems().add(new Process(nextChar++,randomizer.nextInt(5)+1, vals[i], burstVals[i]));
            processes.add(new FCFS(nextChar++,randomizer.nextInt(5)+1, vals[i], burstVals[i]));
        }
        processes.sort(Comparator.comparingInt(Process::getArrival));
        for (Process p: processes){
            p.schedule(currentIter, quantum);
            currentIter = ((FCFS)p).getFinishTime();
            table.getItems().add(p);
        }
        for(int i=0; i <32;i++){
            final int col = i;
            TableColumn<Process, Boolean> mark = new TableColumn<>(Integer.toString(col+1));
            table.getColumns().add(mark);
            mark.setCellValueFactory(cellData -> new ReadOnlyBooleanWrapper(cellData.getValue().getTimes(col+1)));
            mark.setCellFactory(CheckBoxTableCell.<Process>forTableColumn(mark));
        }
        VBox vbox = new VBox(table);

        Scene scene = new Scene(vbox,240,100);
        priStage.setScene(scene);
        priStage.show();
    }

    public void generateNumbers(int count, int sum){
        java.util.Random g = new java.util.Random();

        vals = new int[count];
        sum -= count;

        for (int i = 0; i < count-1; ++i) {
            vals[i] = g.nextInt(sum);
        }
        vals[count-1] = sum;

        java.util.Arrays.sort(vals);
        for (int i = count-1; i > 0; --i) {
            vals[i] -= vals[i-1];
        }
        for (int i = 0; i < count; ++i) { ++vals[i]; }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
