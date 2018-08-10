package hello;

import java.io.File;
import java.security.PublicKey;
import java.security.Security;
import java.util.concurrent.atomic.AtomicLong;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import crytography.RSACryto;
import lombok.extern.java.Log;
@Log
@RestController
public class GreetingController {

    private static final String template = "%s";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    private RSACryto rsaCryto;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	
    	Security.addProvider(new BouncyCastleProvider());
		log.info("BouncyCastle provider added.");
    	try {
    	
    	PublicKey publicKey = rsaCryto.generatePublicKey( new File("id_rsa.pub" ));
    	name = rsaCryto.decrypt(publicKey, name);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
