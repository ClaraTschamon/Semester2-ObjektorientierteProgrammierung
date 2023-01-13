package at.fhv.cts1614.importantExercises.BoundQueue;

public class Job {
    private String description;

    public Job(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return "Job{" +
                "description='" + description + '\'' +
                '}';
    }
}
