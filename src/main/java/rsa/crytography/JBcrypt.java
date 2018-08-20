package rsa.crytography;
import org.mindrot.jbcrypt.BCrypt;

public class JBcrypt {

	public static void main(String[] args) {
		
		String password="secret";

		String hashed1 = BCrypt.hashpw(password, BCrypt.gensalt());

		String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(12));

		if (BCrypt.checkpw(password, hashed1))
			System.out.println("It matches : " +hashed1);
		else
			System.out.println("It does not match");
		
		if (BCrypt.checkpw(password, hashed2))
			System.out.println("It matches : "+hashed2);
		else
			System.out.println("It does not match");
	}

}
