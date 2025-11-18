package tripleo.elijah.work;

import org.jetbrains.annotations.NotNull;

public interface WorkManager {
    void addJobs(WorkList aList);

    void drain();

    @NotNull WorkJob_R next();

    void setDone(WorkList aWorkList);

    boolean isDone(WorkList aWorkList);

    enum WorkJob_E {EMPTY, NEXT, NULL, ITEM}

    record WorkJob_R(WorkJob_E e, WorkJob item) { }
}
