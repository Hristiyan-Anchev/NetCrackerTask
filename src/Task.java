import java.time.LocalDateTime;
import java.util.Date;

public class Task {

    public Task(Integer priority, String name) {
        this.priority = priority;
        this.name = name;
        this.setDueDate(new Date());
    }

    public Task(Integer priority, String name, Date dueDate) {
        this.priority = priority;
        this.name = name;
        this.dueDate = dueDate;
    }

    private Integer priority;
    private String name;
    private Date dueDate;

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
