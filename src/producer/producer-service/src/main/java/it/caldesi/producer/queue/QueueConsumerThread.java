package it.caldesi.producer.queue;

import org.apache.log4j.Logger;

import it.caldesi.domain.EventBean;

public class QueueConsumerThread extends Thread {

	private static final Logger logger = Logger.getLogger(QueueConsumerThread.class);

	private volatile boolean execute = true;

	private long sleepTime;

	public QueueConsumerThread(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	@Override
	public void run() {
		while (execute) {
			EventBean event;
			EventQueueManager eventQueueManager = EventQueueManager.getInstance();
			while ((event = eventQueueManager.getEvent()) != null) {
				try {
					eventQueueManager.sendEvent(event);
				} catch (Exception e) {
					handleUnsentEvent(event, e);
				}
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
				}
			}
		}

		logger.debug("Thread " + this.getName());
	}

	private void handleUnsentEvent(EventBean event, Exception e) {
		logger.error("Cannot send event", e);
	}

	public void stopThread() {
		execute = false;
	}

}
