/*
 *
 *  *     Copyright 2023-2024 Sandeep Chatterjee @ https://sandeepc.in/
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *          http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package in.sandeep.SpringInterviewPro.utility;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The type PurchaseOrderFileReader.
 *
 * @author sandeep
 * @version 1.0
 */
public class PurchaseOrderFileReader {

    private Map<String, String> suppliers;

    /**
     * Gets Supplier Names.
     *
     * @return the Supplier Names
     * @throws IOException the IOException
     */
    public Map<String, String> getSuppliers() throws IOException {

        InputStream inputStream = new FileInputStream ("/home/sandeep/11_Repositories/intellij-idea-workspace/SpringInterviewPro/src/main/resources/static/assets/export29913_FINAL.xls");

        suppliers = new HashMap<String, String> ();

        Workbook workbook = new HSSFWorkbook (inputStream);

        HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt (0);

        Iterator<Row> iterator = sheet.iterator ();

        // Iterating all the rows
        while (iterator.hasNext ()) {
            Row nextRow = iterator.next ();

            Iterator<Cell> cellIterator = nextRow.cellIterator ();

            // Iterating all the columns in a row
            while (cellIterator.hasNext ()) {

                Cell cell = cellIterator.next ();

                switch (cell.getCellType ()) {
                    case STRING:
                        System.out.print (cell.getStringCellValue ());
                        break;
                    case BOOLEAN:
                        System.out.print (cell.getBooleanCellValue ());
                        break;
                    case NUMERIC:
                        System.out.print (cell.getNumericCellValue ());
                        break;
                    default:
                        break;
                }
                System.out.print (" | ");
            }
            System.out.println ();
        }

        // Closing the workbook and input stream
        workbook.close ();

        inputStream.close ();

        return suppliers;
    }
}
