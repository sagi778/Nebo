package main.data_frame;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class Column <E>{

    //constants
    private static int DEFAULT_ROWS = 10;

    //data attributes
    private String colName = "Column";
    private int colNumber = 0;
    private int rows = DEFAULT_ROWS;
    private ArrayList<String> data = new ArrayList<String>();

    //visual attributes
    private TextField[] textFields;
    private Button header;

    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }

    public Column(String colName){
        this.colName = colName;
    }
    public Column(String colName, int colNumber){

        this.colName = colName;
        this.colNumber = colNumber;
    }
    public Column(TextField[] textFields, int colNumber) {

        this.colNumber = colNumber;
        this.rows = textFields.length;
        this.textFields = textFields;
        this.header = new Button(colName + colNumber);
    }

    public void drawColumn(GridPane grid, int columnNumber){

        int COLUMN_SHIFT = 3; // for creating space for table details on the left side

        for(int row=0; row < this.getRows(); row++){

            if( isHeader( row ) ){
                grid.add( drawColumnHeader(), columnNumber + COLUMN_SHIFT,row );
            }
            else {
                grid.add( drawColumnDataRow(row), columnNumber + COLUMN_SHIFT,row ); /////////////////////////////////////////////////////
            }

        }

    }
    private Button drawColumnHeader(){
        header = new Button( this.getColName() );
        header.setPrefSize(this.getColName().length()+80,30);
        return header;
    }
    private TextField drawColumnDataRow(int row){
        textFields[row] = new TextField( this.data.get(row) );
        textFields[row].setPrefSize( drawColumnHeader().getPrefWidth(), 30);
        textFields[row].setAlignment(Pos.CENTER);
        textFields[row].setFont(Font.font(null, FontWeight.LIGHT, 12));
        return textFields[row];
    }

    private boolean isHeader(int row){
        if( row == 0 )
            return true;
        return false;
    }

    public int getColNumber() {
        return colNumber;
    }

    public String getColName() {
        return colName;
    }
    public void addEntry(String entry){
        data.add(entry);
        setRows( getRows()+1 );
        textFields = new TextField[this.getRows() + 1];
        textFields[this.getRows()] = new TextField();
        textFields[this.getRows()].setText((String) entry);


    }
}


