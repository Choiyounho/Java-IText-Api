package utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;

public class CommonsConstant {

    private CommonsConstant(){
    }

    public static final Document documentIsA4 = new Document(PageSize.A4);
    public static final Document document = new Document();

    public static final String ROOT_DIRECTORY = "output/";

    public static final String IMAGE_GITHUB_LOGO = "https://mblogthumb-phinf.pstatic.net/MjAxOTEyMTVfMjc4/MDAxNTc2NDE0MTAwNjg1.cp_9N4gi8GOe7idQjx6pC1LUhK9EqpIs9uArKqZq6iUg.1vF6bTjG3vJW4mb_WagZ5gh0gfwjoo2bznBTEs-tyXkg.JPEG.nilsine11202/github.jpg?type=w800";
    public static final String IMAGE_OCTOCAT = "input/github-octocat.png";

    public static final String FONT = "malgun.ttf";
    public static final int FONT_TITLE_SIZE = 12;
    public static final int FONT_ROWS_SIZE = 10;

    public static final String TITLE = "제목";
    public static final String AUTHOR = "저자";
    public static final String COMPANY = "출판사";
    public static final String IMAGE = "사진";
    public static final String[] TABLE_TITLE = new String[]{TITLE, AUTHOR, COMPANY, IMAGE};


}
