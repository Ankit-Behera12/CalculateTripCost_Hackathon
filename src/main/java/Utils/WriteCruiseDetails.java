package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteCruiseDetails {
    public static File file;
    public static FileOutputStream fos;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static int rowCount=0;

    public WriteCruiseDetails() throws FileNotFoundException
    {
        file=new File(System.getProperty("user.dir")+"\\Excel_Output\\CruiseDetails.xlsx");
        workbook=new XSSFWorkbook();
        sheet=workbook.createSheet("cruise_details");
    }

    public void writeToExcel(String data) throws IOException
    {
        row=sheet.createRow(rowCount);
        cell=row.createCell(0);
        cell.setCellValue(data);
        rowCount++;

        fos=new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
    }
}

