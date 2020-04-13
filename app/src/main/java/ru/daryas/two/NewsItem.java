package ru.daryas.two;

import java.util.Date;

public class NewsItem  {

    private final String Section;
    private final String Title;
    private final String PreviewText;
    private final String PullText;
    private final Date PublishDate;
    private final String ImageUrl;



    public NewsItem(String Section, String Title,String PreviewText, String FullText, Date PublishDate, String ImageUrl  ) {
        this.Section = Section;
        this.Title = Title;
        this.PreviewText = PreviewText;
        this.PullText = FullText;
        this.PublishDate = PublishDate;
        this.ImageUrl = ImageUrl;
    }

    public String getSection() {
        return Section;
    }

    public String getTitle() {
        return Title;
    }

    public String getPreviewText() {
        return PreviewText;
    }

    public String getFullText() {
        return PullText;
    }


    public String getPublishDate() {String formattedDate = String.format("%1$tb %1$te, %1$tY %1$tI:%1$tM %1$Tp", PublishDate);
        return formattedDate;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

}

