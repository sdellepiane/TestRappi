package pe.projects.rappi.testrappi.app.ui.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import pe.projects.rappi.testrappi.Constants;

public class DateUtil {

    public static String formatDate(String date, String inputFormat, String outputFormat){
        String outputDate = Constants.EMPTY_STRING;
        Date parsed;

        SimpleDateFormat dfInput = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
        SimpleDateFormat dfOutput = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());
        try {
            parsed = dfInput.parse(date);
            outputDate = dfOutput.format(parsed);
        } catch (Exception e) {
            Log.e("formattedDateFromString", "Exception in formateDateFromstring(): " + e.getMessage());
        }
        return outputDate;
    }
}
