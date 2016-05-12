/**
 *
 */
package org.alloydcs.fx.cleanup;

import java.util.ArrayList;

/**
 * @author sebastian
 *
 */
public abstract class CleanupService {

	private final ArrayList<CleanupJob> jobs;

	public CleanupService() {
		this.jobs = new ArrayList<CleanupJob>();
	}

	public void addJob(final CleanupJob job) {
		this.jobs.add(job);
	}

	protected void cleanup() {
		this.jobs.forEach(job -> job.cleanup());
	}

}
