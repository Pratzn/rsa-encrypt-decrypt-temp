package crytography;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Log
@Component
public class RSACryto {

	static {

		Security.addProvider(new BouncyCastleProvider());
		log.info("BouncyCastle provider added.");
	}

	public static void main(String args[]) {
		try {
			
			RSACryto rsaCryto = new RSACryto();
			File id_rsa = new File("id_rsa");
			File id_rsa_pub = new File("id_rsa.pub");

			if (!id_rsa.exists() || !id_rsa_pub.exists()) {
				rsaCryto.generateRSAKeypair(4096);
			}
			
			PrivateKey privateKey = rsaCryto.generatePrivateKey(id_rsa );

			log.info(String.format("Instantiated private key: %s", privateKey));

			PublicKey publicKey = rsaCryto.generatePublicKey( id_rsa_pub );
			
			log.info(String.format("Instantiated public key: %s", publicKey));
			
			String message = "test ja";
			
			String encrypted = rsaCryto.encrypt(privateKey, message);
			
			log.info("encrypted: "+encrypted);
			
			String decrypted = rsaCryto.decrypt(publicKey, encrypted);
			
			log.info("decrypted: "+decrypted);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String encrypt(PrivateKey privateKey, String message) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] encrypted = cipher.doFinal(message.getBytes());
		return new String(Base64.encode(encrypted));
	}

	public String decrypt(PublicKey publicKey, String encrypted) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] cipherText = Base64.decode(encrypted.getBytes());
		return new String(cipher.doFinal(cipherText));
	}

	public PrivateKey generatePrivateKey( File filename)
			throws InvalidKeySpecException, FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
		KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
		PemFile pemFile = new PemFile(filename.getAbsolutePath());
		byte[] content = pemFile.getPemObject().getContent();
		PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
		return factory.generatePrivate(privKeySpec);
	}

	public PublicKey generatePublicKey( File filename)
			throws InvalidKeySpecException, FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
		KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
		PemFile pemFile = new PemFile(filename.getAbsolutePath());
		byte[] content = pemFile.getPemObject().getContent();
		X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
		return factory.generatePublic(pubKeySpec);
	}

	public void generateRSAKeypair(int keySize)
			throws NoSuchAlgorithmException, NoSuchProviderException, FileNotFoundException, IOException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
		generator.initialize(keySize);

		KeyPair keyPair = generator.generateKeyPair();
		log.info("RSA key pair generated.");

		RSAPrivateKey priv = (RSAPrivateKey) keyPair.getPrivate();
		RSAPublicKey pub = (RSAPublicKey) keyPair.getPublic();

		writePemFile(priv, "RSA PRIVATE KEY", "id_rsa");
		writePemFile(pub, "RSA PUBLIC KEY", "id_rsa.pub");

	}

	private void writePemFile(Key key, String description, String filename)
			throws FileNotFoundException, IOException {
		PemFile pemFile = new PemFile(key, description);
		pemFile.write(filename);
		log.info(String.format("%s successfully writen in file %s.", description, filename));
	}

	public class PemFile {

		private PemObject pemObject;

		public PemFile(Key key, String description) {
			this.pemObject = new PemObject(description, key.getEncoded());
		}

		public PemFile(String filename) throws FileNotFoundException, IOException {
			PemReader pemReader = new PemReader(new InputStreamReader(new FileInputStream(filename)));
			try {
				this.pemObject = pemReader.readPemObject();
			} finally {
				pemReader.close();
			}
		}

		public void write(String filename) throws FileNotFoundException, IOException {
			PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)));
			try {
				pemWriter.writeObject(this.pemObject);
			} finally {
				pemWriter.close();
			}
		}

		public PemObject getPemObject() {
			return pemObject;
		}
	}

}
