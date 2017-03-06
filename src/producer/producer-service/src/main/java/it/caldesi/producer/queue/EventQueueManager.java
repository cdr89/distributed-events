package it.caldesi.producer.queue;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import it.caldesi.domain.EventBean;
import it.caldesi.producer.services.EventSenderService;

public class EventQueueManager {

	private static final Logger logger = Logger.getLogger(EventQueueManager.class);

	private static EventQueueManager instance;

	private ConcurrentLinkedQueue<EventBean> queue = new ConcurrentLinkedQueue<>();
	private LinkedList<QueueConsumerThread> consumerThreads = new LinkedList<>();

	private EventQueueManager() {
	}

	public static EventQueueManager getInstance() {
		if (instance == null)
			instance = new EventQueueManager();
		return instance;
	}

	public void queue(EventBean eventBean) {
		queue.offer(eventBean);
	}

	public EventBean getEvent() {
		return queue.poll();
	}

	public void registerQueueConsumerThread(QueueConsumerThread consumerThread) {
		consumerThreads.add(consumerThread);
	}

	public void sendEvent(EventBean eventBean) throws Exception {
		EventSenderService.sendEvent(eventBean);
	}

	public void destroy() {
		for (QueueConsumerThread thread : consumerThreads) {
			logger.debug("Destroying thread: " + thread);
			thread.stopThread();
		}
	}

}