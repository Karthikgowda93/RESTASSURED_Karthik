package courses;

import java.util.ArrayList;
import java.util.List;

public class GoogleApiproduct {

    private int accuracy;
    private String name;
    private String phone_number;
    private String address;
    private String website;
    private String language;
    private location location;
    private List<String> types;

   /* public GoogleApiproduct(int accuracy,String name,String phone_number,String address, String Website,String language,location location,List<String> types ){

        String first = "", second="";
        location lc = new location();
        int lat = 0;
        int lag = 0;
        lc.setLat(lat);
        lc.setLng(lag);
        setLocation(lc);
        setAccuracy(accuracy);
        setName(name);
        setPhone_number(phone_number);
        List<String> mylist = new ArrayList<String>();
        mylist.add(first);
        mylist.add(second);
        setTypes(mylist);
        setAddress(address);
        setLanguage(language);
    }*/
    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public courses.location getLocation() {
        return location;
    }

    public void setLocation(courses.location location) {
        this.location = location;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
