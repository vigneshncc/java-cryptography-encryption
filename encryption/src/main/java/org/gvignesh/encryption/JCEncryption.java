/**
 * JCEncryption.java
 * com.piqube.jcencryption
 * @author vignesh
 * @date 28-Apr-2014
 * @time 2:21:17 pm
 */
package org.gvignesh.encryption;

import java.security.Key;

import org.apache.log4j.Logger;
import org.gvignesh.encryption.exception.NoKeyFoundException;
import org.gvignesh.jcencryption.AESCipher;
import org.gvignesh.jcencryption.KeyEncoding;
import org.gvignesh.jcencryption.KeystoreUtil;

/**
 * @author vignesh
 * 
 */
public final class JCEncryption {
	
	private static final Logger LOG = Logger.getLogger(JCEncryption.class);
	private static Key keyFromKeyStore = null;
	private static JCEncryption jcEncryption;

	/**
	 * @param keyLocation
	 */
	private JCEncryption(String keyLocation) {
		setKey(keyLocation);
	}

	/**
	 * @return the keyFromKeyStore
	 */
	public static Key getKeyFromKeyStore() {
		return keyFromKeyStore;
	}

	/**
	 * @param keyFromKeyStore
	 *            the keyFromKeyStore to set
	 */
	public static void setKeyFromKeyStore(Key keyFromKeyStore) {
		JCEncryption.keyFromKeyStore = keyFromKeyStore;
	}

	/**
	 * @return the jcEncryption
	 */
	public static JCEncryption getJcEncryption() {
		return jcEncryption;
	}

	/**
	 * @param jcEncryption
	 *            the jcEncryption to set
	 */
	public static void setJcEncryption(JCEncryption jcEncryption) {
		JCEncryption.jcEncryption = jcEncryption;
	}

	/**
	 * @param fileLocation
	 * @return
	 */
	public static JCEncryption getInstance(String fileLocation) {
		if (null == keyFromKeyStore) {
			LOG.info("New Instance created");
			setJcEncryption(new JCEncryption(fileLocation));
			return jcEncryption;
		}
		return jcEncryption;
	}

	/**
	 * @param keyLocation
	 */
	private static void setKey(String keyLocation) {
		LOG.info("The KeyLocation is "+keyLocation);
		String keystoreFileLocation = keyLocation;
		String storePass = "vigneshkey";
		String alias = "gvignesh";
		String keyPass = "vigneshkey";
		Key key = KeystoreUtil.getKeyFromKeyStore(keystoreFileLocation,
				storePass, alias, keyPass);
		setKeyFromKeyStore(key);
		LOG.info("The key is set");
	}

	/**
	 * @param cipher
	 * @param message
	 * @return
	 */
	private static String decrypt(AESCipher cipher, String encryptedMessage) {
		LOG.info("Inside decrypt method with message: "+encryptedMessage);
		return cipher.getDecryptedMessage(encryptedMessage);
	}

	/**
	 * @param cipher
	 * @param message
	 * @return
	 */
	private static String encrypt(AESCipher cipher, String message) {
		LOG.info("Inside encrypt method with message:  "+message);
		return cipher.getEncryptedMessage(message);
	}

	/**
	 * @throws NoKeyFoundException 
	 * 
	 */
	public void showKeys() throws NoKeyFoundException {
		if(null==keyFromKeyStore)
			throw new NoKeyFoundException("Call Static method of this class with keylocation as argument");
		AESCipher cipher = new AESCipher(keyFromKeyStore);
		LOG.debug("\n\nPrint SecretPrivateKey from JCEKS Keystore\n===========================================");
		LOG.debug("Key (Base64 Encoded): "
				+ cipher.getKey(KeyEncoding.BASE64));
		LOG.debug("Key (Hex Encoded): "
				+ cipher.getKey(KeyEncoding.HEX));
		LOG.debug("Key (Base32 Encoded): "
				+ cipher.getKey(KeyEncoding.BASE32));
	}

	/**
	 * @param secretKey
	 * @param messageToEncrypt
	 * @return
	 * @throws NoKeyFoundException 
	 */
	public String encryptString(String secretKey, String messageToEncrypt) throws NoKeyFoundException {
		if(null==keyFromKeyStore)
			throw new NoKeyFoundException("Call Static method of this class with keylocation as argument");
		String iv = secretKey;
		AESCipher cipher;
		Key keyFromStore = keyFromKeyStore;
		cipher = new AESCipher(keyFromStore, iv.getBytes());
		return encrypt(cipher, messageToEncrypt);
	}

	/**
	 * @param secretKey
	 * @param messageToDecrypt
	 * @return
	 * @throws NoKeyFoundException 
	 */
	public String decryptString(String secretKey, String messageToDecrypt) throws NoKeyFoundException {
		if(null==keyFromKeyStore)
			throw new NoKeyFoundException("Call Static method of this class with keylocation as argument");
		String iv = secretKey;
		AESCipher cipher;
		Key keyFromStore = keyFromKeyStore;
		cipher = new AESCipher(keyFromStore, iv.getBytes());
		return decrypt(cipher, messageToDecrypt);
	}
}