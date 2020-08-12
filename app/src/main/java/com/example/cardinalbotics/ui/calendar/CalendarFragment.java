package com.example.cardinalbotics.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cardinalbotics.EventDecorator;
import com.example.cardinalbotics.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;

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
    }
}