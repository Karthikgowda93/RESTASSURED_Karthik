package Models;

public class productsPayload {

    private String name ;
    private String isbn;
    private String aisle;
    private String author;
    private String ID;

    public productsPayload(){

    }
    public productsPayload(String name, String isbn, String aisle, String author){
        setName(name);
        setIsbn(isbn);
        setAisle(aisle);
        setAuthor(author);
    }
    // for Delete request
    public  productsPayload(String ID){
        setID(ID);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
