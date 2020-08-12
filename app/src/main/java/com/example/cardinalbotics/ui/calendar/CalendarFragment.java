package com.example.cardinalbotics.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cardinalbotics.EventDecorator;
import com.example.cardinalbotics.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarFragment extends Fragment implements OnDateSelectedListener {

    private CalendarViewModel calendarViewModel;
//    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");
    TextView textView;
    MaterialCalendarView widget;
//    ArrayList<CalendarDay> eventDay;
//    ArrayList<String> eventName;
    String eventName="Ayaka's Birthday";
    CalendarDay eventDay=CalendarDay.from(2020, 8, 14);
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ArrayList<CalendarDay> dates = new ArrayList<>();
        dates.add(CalendarDay.from(2020, 8, 14));
        EventDecorator decor = new EventDecorator(0, dates);

        ((MaterialCalendarView) view.findViewById(R.id.calendarView)).invalidateDecorators();
        ((MaterialCalendarView) view.findViewById(R.id.calendarView)).addDecorator(decor);

        textView=view.findViewById(R.id.calendartext);
        widget= view.findViewById(R.id.calendarView);
        widget.setOnDateChangedListener(this);
        textView.setText("No Selection");
    }
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        if(eventDay.equals(date)){
            textView.setText(eventName);
        }else{
            textView.setText("No Event");
        }
    }

}