package transferpdf.domain;

public class ExcelVo {

    private String title;
    private String author;
    private String company;
    private String isbn;
    private String imageUrl;

    private ExcelVo(String title, String author, String company, String isbn, String imageUrl) {
        this.title = title;
        this.author = author;
        this.company = company;
        this.isbn = isbn;
        this.imageUrl = imageUrl;
    }

    public static ExcelVo print(String[] cellArr) {
        String title = cellArr[0];
        String author = cellArr[1];
        String company = cellArr[2];
        String isbn = cellArr[3];
        String imageUrl = cellArr[4];
        return new ExcelVo(title, author, company, isbn, imageUrl);
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getImageUrl() {
        return "input/" + imageUrl;
    }

    @Override
    public String toString() {
        return "console.transfer.domain.transfer.domain.transferpdf.domain.ExcelVo{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", company='" + company + '\'' +
                ", isbn='" + isbn + '\'' +
                ", imageurl='" + imageUrl + '\'' +
                '}';
    }

}
