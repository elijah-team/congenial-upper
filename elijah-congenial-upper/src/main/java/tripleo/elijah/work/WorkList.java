package tripleo.elijah.work;

import com.google.common.collect.ImmutableList;
import org.jetbrains.annotations.NotNull;

public interface WorkList {
//    void addJob(WorkJob aJob);
//
//    @NotNull Iterable<WorkJob> getJobs();
//
//    boolean isEmpty();

    void addJob(WorkJob aJob);

    @NotNull ImmutableList<WorkJob> getJobs();

    boolean isDone();

    boolean isEmpty();

    void setDone();
}
