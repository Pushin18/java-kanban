import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    @Override
    public String toString() {
        return "Epic{" +
                ", name='" + getName() + '\'' +
                ", overview='" + getOverview() + '\'' +
                '}' +
                "subtaskIds=" + subtaskIds +
                '}';
    }

    public Epic(String name, String overview) {
        super(name, overview);
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void setSubtaskIds(List<Integer> subtaskIds) {
        this.subtaskIds = subtaskIds;
    }

    private List<Integer> subtaskIds = new ArrayList<>(); // лучше так

}