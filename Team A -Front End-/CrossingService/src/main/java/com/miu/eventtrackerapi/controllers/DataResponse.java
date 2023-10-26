package com.miu.eventtrackerapi.controllers;

import java.util.List;

public class DataResponse<T> {
    private List<T> data;

    public DataResponse(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }
}
