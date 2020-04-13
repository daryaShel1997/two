package ru.daryas.two.Gif;

public class GifDTO {


    private UserDTO user;
    private ImagesDTO images;


    public String getUrl() {
        return images.getOriginal().getUrl();
    }
}
