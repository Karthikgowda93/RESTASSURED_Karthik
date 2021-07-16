package Models;

public class Product {

    private String title ;
    private int id;
    private int userId;
    private String body;

    public Product(){

    }
    // for Post Request
    public Product(String title,String body,int userId){
        setBody(body);
        setUserId(userId);
        setTitle(title);
    }

    // constructor for put request
    public Product(String title, String body, int userId,int id) {
        setBody(body);
        setUserId(userId);
        setTitle(title);
        setId(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
