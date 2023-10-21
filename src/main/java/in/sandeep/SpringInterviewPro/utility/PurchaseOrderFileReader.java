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
import java.util.ArrayList;
import java.util.List;

/**
 * The type PurchaseOrderFileReader.
 *
 * @author sandeep
 * @version 1.0
 */
public class PurchaseOrderFileReader {

    private final Integer columnIndex = 11;

    private List<String> suppliers;

    /**
     * Gets Supplier Names.
     *
     * @return the Supplier Names
     * @throws IOException the IOException
     */
    public List<String> getSuppliers() throws IOException {

        InputStream inputStream = new FileInputStream ("/home/sandeep/11_Repositories/intellij-idea-workspace/SpringInterviewPro/src/main/resources/static/assets/export29913_FINAL.xls");
        suppliers = new ArrayList<String> ();
        Workbook workbook = new HSSFWorkbook (inputStream);
        HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt (0);
        for (Row row : sheet) { // For each Row.
            Cell cell = row.getCell (columnIndex); // Get the Cell at the Index/Column you want.
            if (!suppliers.contains (cell.getStringCellValue ())) {
                suppliers.add (cell.getStringCellValue ());
            }
        }
        workbook.close ();
        inputStream.close ();
        return suppliers;
    }
}
