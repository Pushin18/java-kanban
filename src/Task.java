public class Task {
    private String name;
    private Status status;
    private int id;
    private String overview;

    public Task(String name, String overview, Status status) {
        this.name = name;
        this.overview = overview;
        this.status = status;
    }

    public Task(String name, String overview) {
        this.name = name;
        this.overview = overview;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", overview='" + overview + '\'' +
                '}';
    }

}