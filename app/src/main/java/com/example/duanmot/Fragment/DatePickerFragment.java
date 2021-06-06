package com.example.duanmot.Fragment;

import androidx.fragment.app.DialogFragment;
import com.example.duanmot.DAO.DAOHoaDon;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {
    DAOHoaDon daoHoaDon;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private int year, month, day;
    public DatePickerFragment() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
    }
}