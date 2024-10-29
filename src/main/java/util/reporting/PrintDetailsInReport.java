package util.reporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class PrintDetailsInReport {

    public static void printBodyParameter(String bodyParameter) {
        ExtentTest test = ExtentTestManager.getTest();
        ExtentHelper.logInfoEvent("Body Parameter: ");
        Markup m = MarkupHelper.createCodeBlock(bodyParameter, CodeLanguage.JSON);
        test.info(m);
    }

    public static void printMethodAndPath(String method, String path) {
        ExtentHelper.logInfoEvent("Method: " + method + ", URL: " + path);
    }
}
