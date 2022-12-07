package st20224015_proSecurity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class CipherStreamBean {
	private static CipherStreamBean instance = new CipherStreamBean();
	private SecretKey secretKey = null;

	private CipherStreamBean() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			secretKey = keyGenerator.generateKey();
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
	}

	public static CipherStreamBean getInstance() {
		return instance;
	}

	public void encryptFile(String fileName) throws Exception {
		int index1 = fileName.lastIndexOf("\\");
		int index2 = fileName.lastIndexOf(".");
		
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		
		String tmpFileName1 = fileName.substring(0, index1 + 1) + now + fileName.substring(index2);

		// 암호화
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		InputStream input = null;
		OutputStream output = null;

		try {
			input = new BufferedInputStream(new FileInputStream(fileName));
			output = new CipherOutputStream(new BufferedOutputStream(new FileOutputStream(tmpFileName1)), cipher);
			int size = 0;
			byte[] buffer = new byte[1024];
			while ((size = input.read(buffer)) != -1) {
				output.write(buffer, 0, size);
			}
		} catch (Exception e) {
			System.out.println("Exception[Encryption] : " + e.getLocalizedMessage());
		} finally {
			try {
				if (output != null) {
					output.close();
				}
				if (input != null) {
					input.close();
				}
			} catch (Exception e) {
				System.out.println("Exception[close] : " + e.getMessage());
			}
			System.out.println("파일 암호화 종료");
		}

	}

	public void decryptFile(String fileName) throws Exception {
		int index1 = fileName.lastIndexOf("\\");
		int index2 = fileName.lastIndexOf(".");
		
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		
		String tmpFileName2 = fileName.substring(0, index1 + 1) + now + fileName.substring(index2);

		// 복호화
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		InputStream input = null;
		OutputStream output = null;

		try {
			input = new BufferedInputStream(new FileInputStream(fileName));
			output = new CipherOutputStream(new BufferedOutputStream(new FileOutputStream(tmpFileName2)), cipher);
			int size = 0;
			byte[] buffer = new byte[1024];
			while ((size = input.read(buffer)) != -1) {
				output.write(buffer, 0, size);
			}
		} catch (Exception e) {
			System.out.println("Exception[Decryption] : " + e.getLocalizedMessage());
		} finally {
			try {
				if (output != null) {
					output.close();
				}
				if (input != null) {
					input.close();
				}
			} catch (Exception e) {
				System.out.println("Exception[close] : " + e.getMessage());
			}
			System.out.println("파일 복호화 종료");
		}

	}
}
