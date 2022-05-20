package be;

public class ObservationNote {

    private int id;
    private int citizenId;
    private String date;
    private String note;

    public ObservationNote(int id, int citizenId, String date, String note) {
        this.id = id;
        this.citizenId = citizenId;
        this.date = date;
        this.note = note;
    }

    public ObservationNote(int citizenId, String date, String note) {
        this.citizenId = citizenId;
        this.date = date;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(int citizenId) {
        this.citizenId = citizenId;
    }
}
