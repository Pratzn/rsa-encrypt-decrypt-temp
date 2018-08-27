package rsa.service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class AsynService {

	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("rawtypes")
	@Async
	public CompletableFuture<String> asynMethod() {
//		ResponseEntity<?> results = restTemplate.getForObject("http://google.co.th", ResponseEntity.class);
		String results=null;
		try {
			results = new Task().sampleTask();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CompletableFuture.completedFuture(results);
	}

	static class Task {
		public String sampleTask() throws Exception {
			
			Random random = new Random();
			int sec = random.nextInt(10)*1000;
			Thread.currentThread().sleep(sec);
			String ret = "sleep:"+sec;
			System.out.println(Thread.currentThread().getName()+ret);			
			return ret;
		}
	}
}
