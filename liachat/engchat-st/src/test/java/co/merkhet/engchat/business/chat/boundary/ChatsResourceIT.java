package co.merkhet.engchat.business.chat.boundary;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Rule;
import org.junit.Test;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;

import static com.airhacks.rulz.jaxrsclient.JAXRSClientProvider.*;

public class ChatsResourceIT {

	@Rule
	public JAXRSClientProvider provider = buildWithURI("http://localhost:8080/engchat/resources/chats");

	//@Test
	public void testTalk() {
		Response response = this.provider.target().queryParam("request", "this is the family")
				.request(MediaType.TEXT_PLAIN).get();
		assertThat(response.getStatus(), is(200));
		String payload = response.readEntity(String.class);
		System.out.println("******** " + payload);
		assertTrue(payload.startsWith("Hello"));
	}

	@Test
	public void testTheFamily() {
		Response response = this.provider.target().queryParam("request", "Estoy bien")
				.request(MediaType.TEXT_PLAIN).get();
		assertThat(response.getStatus(), is(200));
		String payload = response.readEntity(String.class);
		System.out.println("******** " + payload);
		assertTrue(payload.startsWith("Hola, ¿c\u00f3mo est\u00e1s?"));
		
		response = this.provider.target().queryParam("request", "WHAT SERIES YOUR ENGINE IS")
				.request(MediaType.TEXT_PLAIN).get();
		assertThat(response.getStatus(), is(200));
		payload = response.readEntity(String.class);
		System.out.println("******** " + payload);
		assertTrue(payload.startsWith("Excelente"));
		
	}

}
