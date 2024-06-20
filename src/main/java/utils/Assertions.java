package utils;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import report.ReportTestManager;

public class Assertions {

    public static void stringContains(String actual, String expected, String messageOnFailure){
        try {
            Assert.assertTrue(actual.contains(expected));
            ReportTestManager.getTest().log(Status.PASS, "Title verified : " + actual);
        } catch (AssertionError as){
            Assert.fail(messageOnFailure + " : Actual '" + actual + "' , Expected '" + expected + "'");
            //ReportTestManager.getTest().log(Status.FAIL, messageOnFailure + " : Actual '" + actual + "' , Expected '" + expected + "'");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
