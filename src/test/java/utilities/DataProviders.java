package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "Login Data")
	public String[][] getdata() throws IOException {

		String path = System.getProperty("user.dir") + "//testdata//opencartdata.xlsx";

		ExcelUtility xlutil = new ExcelUtility(path);
		int totalrows = xlutil.getrowcount("Sheet1");
		int totalcols = xlutil.getcellcount("Sheet1", 0);

		String[][] logindata = new String[totalrows][totalcols];

		for (int i = 1; i <= totalrows; i++) {
			for (int j = 0; j < totalcols; j++) {
				logindata[i - 1][j] = xlutil.getcelldata("Sheet1", i, j);
			}
		}
		return logindata;
	}

}
