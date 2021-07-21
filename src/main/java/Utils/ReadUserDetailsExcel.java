package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadUserDetailsExcel {

    //This function is used to get the path of the Excel File
    public static String getExcelPath() {

        //Here path is getting the user directory, which is to be used to get the full path of the Excel file
        String path = System.getProperty("user.dir");
        String full_path = path + "/Excel_Input/Holiday_Homes_User_Details.xlsx";
        return full_path;

    }

    //This function is used to retrieve the number of guests from Excel Sheet
    public static int ReadNumberOfGuest() throws IOException {

        String SheetName = "UserDetails";
        int Number_of_Guest = 0;
        //FileInputStream is used to access our Excel File
        FileInputStream ffs = new FileInputStream(getExcelPath());
        XSSFWorkbook workbook = new XSSFWorkbook(ffs);
        workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
        int sheets = workbook.getNumberOfSheets();

        for(int i = 0 ; i < sheets; i++){

            if(workbook.getSheetName(i).equalsIgnoreCase(SheetName)) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();
                //The firstrow contains the headings so we will not use it
                Row firstrow = rows.next();
                //Selecting the second row which has our user details
                Row Secondrow = rows.next();
                Iterator<Cell> cells = Secondrow.cellIterator();
                while(cells.hasNext()) {

                    Cell input_value = cells.next();
                    switch (input_value.getCellType())
                    {
                        case NUMERIC:
                            Number_of_Guest = (int) input_value.getNumericCellValue();
                            break;
                    }
                }
            }
        }
        return Number_of_Guest;
    }

    //This function is used to retrieve the Destination Location from Excel Sheet
    public static String ReadDestination() throws IOException {

        String SheetName = "UserDetails";
        String Destination = "";
        //FileInputStream is used to access our Excel File
        FileInputStream ffs = new FileInputStream(getExcelPath());
        XSSFWorkbook workbook = new XSSFWorkbook(ffs);
        workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
        int sheets = workbook.getNumberOfSheets();

        for(int i = 0 ; i < sheets; i++){

            if(workbook.getSheetName(i).equalsIgnoreCase(SheetName)) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();
                //The firstrow contains the headings so we will not use it
                Row firstrow = rows.next();
                //Selecting the second row which has our user details
                Row Secondrow = rows.next();
                Iterator<Cell> cells = Secondrow.cellIterator();
                while(cells.hasNext()) {

                    Cell input_value = cells.next();
                    switch (input_value.getCellType())
                    {
                        case STRING:
                            //System.out.println(input_value.getStringCellValue());
                            Destination = input_value.getStringCellValue();
                            break;
                    }
                }
            }
        }
        return Destination;
    }

    //This function is used to retrieve the dynamically changing "To" and "From" date from Excel Sheet
    public static String[] ReadDates() throws IOException {

        String SheetName = "UserDetails";
        String[] Date = new String[2];
        //FileInputStream is used to access our Excel File
        FileInputStream ffs = new FileInputStream(getExcelPath());
        XSSFWorkbook workbook = new XSSFWorkbook(ffs);
        workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
        int sheets = workbook.getNumberOfSheets();

        for(int i = 0 ; i < sheets; i++){

            if(workbook.getSheetName(i).equalsIgnoreCase(SheetName)) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();
                //The firstrow contains the headings so we will not use it
                Row firstrow = rows.next();
                //Selecting the second row which has our user details
                Row Secondrow = rows.next();
                Iterator<Cell> cells = Secondrow.cellIterator();
                int x = 0;
                while(cells.hasNext()) {

                    Cell input_value = cells.next();
                    switch (input_value.getCellType())
                    {
                        case FORMULA:
                            SimpleDateFormat DtFormat = new SimpleDateFormat("EEE-MMM-dd-yyyy");
                            Date date = input_value.getDateCellValue();
                            //System.out.println(DtFormat.format(date));
                            Date[x] = DtFormat.format(date);
                            x++;
                    }
                }
            }
        }
        return Date;
    }

    public static void main(String[] args) throws IOException {

        ReadUserDetailsExcel x = new ReadUserDetailsExcel();
        String[] arr = new String[2];
        System.out.println(x.ReadNumberOfGuest());
        System.out.println(x.ReadDestination());
        arr = x.ReadDates();

        for(int i = 0 ; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }
}
