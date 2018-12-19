package com.rabeech.runningpacecalculator.logic;

import com.rabeech.runningpacecalculator.view.ViewInterface;
import com.rabeech.runningpacecalculator.data.DataSourceInterface;
import com.rabeech.runningpacecalculator.data.ListItem;

public class Controller {
    private ViewInterface view;

    private DataSourceInterface dataSource;

    public Controller(ViewInterface view, DataSourceInterface dataSource) {
        this.view = view;
        this.dataSource = dataSource;

        getListFromDataSource();
    }

    public void onListItemClick(ListItem selectedItem){
        view.startDetailActivity(
                selectedItem.getSr_date(),
                selectedItem.getSr_time(),
                selectedItem.getId()

        );
    }

    public void getListFromDataSource(){
        view.setUpAdapterAndView(
                dataSource.getListOfData()
        );
    }
}

