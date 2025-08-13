package ge.chat.artmedia.utils;

public class Utils {
    public static void logInfo(String message) {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().info(message);
        }
    }

    public static void logFail(String message) {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().fail(message);
        }
    }

    public static void logPass(String message) {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().pass(message);
        }
    }
    public static void logSkip(String message){
        if(ExtentReportManager.getTest() != null){
            ExtentReportManager.getTest().skip(message);
        }
    }
}
