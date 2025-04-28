/*
 * ElijahLang compiler, copyright Tripleo <oluoluolu+elijah@gmail.com>
 *
 * The contents of this library are released under the LGPL licence v3,
 * the GNU Lesser General Public License text was downloaded from
 * http://www.gnu.org/licenses/lgpl.html from `Version 3, 29 June 2007'
 *
 */
package tripleo.elijah.work;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tripleo.elijah.util.ProgramIsWrongIfYouAreHere;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created 4/26/21 4:22 AM
 */
public class WorkManager {
	private final @NotNull List<WorkList> doneWork  = new ArrayList<WorkList>();
	private final @NotNull List<WorkList> jobs      = new ArrayList<WorkList>();
	private final          List<WorkList> doneLists = new ArrayList<WorkList>();

	public void addJobs(final WorkList aList) {
		jobs.add(aList);
	}

	public void drain() {
		//noinspection InfiniteLoopStatement
		while (true) {
			final var ww = next();
			switch (ww.e()) {
			case ITEM -> {
				@Nullable WorkJob w = ww.item();
				if (w == null) throw new ProgramIsWrongIfYouAreHere("implementation error");
				w.run(this);
			}
				case EMPTY -> {break;}
				case NEXT -> {int y=2;}
				case NULL -> {break;}
			default -> throw new IllegalStateException("Unexpected value: " + ww.e());
			}
		}
	}

	@NotNull
	public WorkJob_R next() {
		int __debug_x=0;
		Iterator<WorkList> workListIterator = jobs.iterator();
		do {
			if (workListIterator.hasNext()) {
				final WorkList workList = workListIterator.next();
				if (!isDone(workList)) {
					for (WorkJob w : workList.getJobs()) {
						if (!w.isDone()) {
							return new WorkJob_R(WorkJob_E.ITEM, w);
						}
					}
					setDone(workList);
				} else {
					workListIterator.remove();
					doneWork.add(workList);
					//return next();
					return new WorkJob_R(WorkJob_E.NEXT, null);
				}
			} else {
				return new WorkJob_R(WorkJob_E.NULL, null);
			}
			__debug_x++;
		} while (false);
		assert __debug_x>=2;
		return null;
	}

	private void setDone(final WorkList aWorkList) {
		this.doneLists.add(aWorkList);
	}

	private boolean isDone(final WorkList aWorkList) {
		return this.doneLists.contains(aWorkList);
	}

	enum WorkJob_E {EMPTY, NEXT, NULL, ITEM}

	record WorkJob_R(WorkJob_E e, WorkJob item) { }
}

//
//
//
