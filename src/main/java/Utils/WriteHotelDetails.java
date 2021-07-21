package Utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteHotelDetails {


    public void WriteToExcel(String[] name, String[] total_amount, String[] charges_pernight) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet Sheet = workbook.createSheet("Hotel_Details");

        Row FirstRow = Sheet.createRow(0);
        Cell cell0 = FirstRow.createCell(0);
        Cell cell1 = FirstRow.createCell(1);
        Cell cell2 = FirstRow.createCell(2);
        cell0.setCellValue("Hotel Name");
        cell1.setCellValue("Charges Per Night");
        cell2.setCellValue("Total Amount");

        int x = 0;
        for(int i = 1; i<=3; i++) {

            Row rows = Sheet.createRow(i);

            Cell col1 = rows.createCell(0);
            col1.setCellValue(name[x]);

            Cell col2 = rows.createCell(1);
            col2.setCellValue(charges_pernight[x]);

            Cell col3 = rows.createCell(2);
            col3.setCellValue(total_amount[x]);

            x++;
        }

        //Setting the path, and using FileOutputStream to save and close the excel file once data is written.
        String path = System.getProperty("user.dir");
        FileOutputStream fout = new FileOutputStream(path + "/Excel_Output/Hotel_Details.xlsx");
        workbook.write(fout);
        fout.close();
        workbook.close();

    }

}

