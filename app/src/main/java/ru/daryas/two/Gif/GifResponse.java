package ru.daryas.two.Gif;

import androidx.annotation.Nullable;

import java.util.List;

public class GifResponse {

    public  List<GifDTO> data;

    @Nullable
    public  List<GifDTO> getData() {
        return data;
    }


}