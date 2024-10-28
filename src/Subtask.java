public class Subtask extends Task {

    public int getEpicID() {
        return epicID;
    }

    public void setEpicID(int epicID) {
        this.epicID = epicID;
    }

    private int epicID;


    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", status=" + getStatus() +
                ", overview='" + getOverview() + '\'' +
                '}' +
                "epicID=" + epicID +
                '}';
    }

    public Subtask(String name, String overview, Status status, int epicID) {
        super(name, overview, status);
        this.epicID = epicID;
    }
}