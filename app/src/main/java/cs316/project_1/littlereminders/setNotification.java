package cs316.project_1.littlereminders;

public class setNotification {

    private int notificationID;
    private String notificationTitle = "";
    private String notificationText = "";

    // constructors
    public setNotification() {}
    public setNotification(int id, String title, String text) {

        this.notificationID = id;
        this.notificationTitle =  title;
        this.notificationText = text;

    }

    // properties
    public void setID(int id) {
        this.notificationID = id;
    }
    public int getID() {
        return this.notificationID;
    }

    public void setnotificationTitle(String title) {
        this.notificationTitle = title;
    }

    public String getnotificationTitle() {
        return this.notificationTitle;
    }

    public void setnotificationText(String text) {
        this.notificationText = text;
    }

    public String getnotificationText() {
        return this.notificationText;
    }
}
