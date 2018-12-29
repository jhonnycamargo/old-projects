package co.merkhet.engchat.business.logging.boundary;

@FunctionalInterface
public interface LogSink {

	void log(String msg);
}