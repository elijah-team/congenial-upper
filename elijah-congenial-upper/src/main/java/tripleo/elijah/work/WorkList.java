/* -*- Mode: Java; tab-width: 4; indent-tabs-mode: t; c-basic-offset: 4 -*- */
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
import org.pcollections.TreePVector;

/**
 * Created 4/26/21 4:24 AM
 */
public class WorkList {
	private org.pcollections.PVector<WorkJob> jobs = TreePVector.empty();

	public void addJob(final WorkJob aJob) {
		jobs = jobs.plus(aJob);
	}

	public @NotNull Iterable<WorkJob> getJobs() {
		return jobs;
	}

	public boolean isEmpty() {
		return jobs.size() == 0;
	}
}

//
// vim:set shiftwidth=4 softtabstop=0 noexpandtab:
//
