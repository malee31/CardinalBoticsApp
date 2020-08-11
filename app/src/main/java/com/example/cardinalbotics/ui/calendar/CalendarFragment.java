package com.example.cardinalbotics.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Response;
import com.example.cardinalbotics.AppSharedResources;
import com.example.cardinalbotics.EventDecorator;
import com.example.cardinalbotics.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                ViewModelProviders.of(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        return root;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ArrayList<CalendarDay> dates= new ArrayList<>();
        dates.add(CalendarDay.from(2020, 8, 14));
        EventDecorator ayaka = new EventDecorator(0, dates);

        ((MaterialCalendarView)view.findViewById(R.id.calendarView)).invalidateDecorators();
        ((MaterialCalendarView)view.findViewById(R.id.calendarView)).addDecorator(ayaka);


    }
}