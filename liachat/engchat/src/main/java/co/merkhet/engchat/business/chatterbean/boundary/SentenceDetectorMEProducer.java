package co.merkhet.engchat.business.chatterbean.boundary;

import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import co.merkhet.engchat.business.chatterbean.ChatterBeanException;
import co.merkhet.engchat.business.logging.boundary.LogSink;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceDetectorMEProducer {

	@Inject
	LogSink LOG;

	@Produces
	@Named("sentenceDetectorME")
	public SentenceDetectorME produce() {
		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("en-sent.bin")) {
			SentenceModel model = new SentenceModel(is);
			SentenceDetectorME sDetectorME = new SentenceDetectorME(model);

			return sDetectorME;
		} catch (IOException e) {
			LOG.log(e.getMessage());
			throw new ChatterBeanException(e);
		}
	}
}
