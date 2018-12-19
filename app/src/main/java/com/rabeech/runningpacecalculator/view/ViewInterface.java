package com.rabeech.runningpacecalculator.view;

import java.util.List;
import com.rabeech.runningpacecalculator.data.ListItem;

public interface ViewInterface {
    void startDetailActivity(String sr_date, String sr_time, String id);

    void setUpAdapterAndView(List<ListItem> listOfData);
}
