package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadCruiseDetailsExcel {


    public static java.util.List<String>  ReadExcelWords() throws IOException {

        List<String> listWords = new ArrayList<String>();
        String excelFilePath=System.getProperty("user.dir")+"/Excel_Input/Cruise_User_Details.xlsx";
        FileInputStream inputstream =new FileInputStream(excelFilePath);
        XSSFWorkbook workbook=new XSSFWorkbook(inputstream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(1).getLastCellNum();
        for(int r=1; r<=rows;r++)
        {
            XSSFRow row=sheet.getRow(r);
            for(int c=0;c<cols;c++)
            {

                XSSFCell cell=row.getCell(c);
                switch (cell.getCellType())
                {
                    case STRING: listWords.add(cell.getStringCellValue());
                        break;
                }
            }
        }
        return listWords;

    }
}
