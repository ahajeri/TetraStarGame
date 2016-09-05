package com.design.pattern.tetrastar.model;
/**
 * 
 *  @author Akshata, Rachna and Shweta.
 *   A co-ordinate on the m * n grid
 */
public class TFaceGrid {

    private int row;

    private int column;

    public TFaceGrid() {

    }

    public TFaceGrid(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}
