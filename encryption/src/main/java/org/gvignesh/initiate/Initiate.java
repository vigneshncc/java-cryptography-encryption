/**
 * Initiate.java
 * com.piqube.initiate
 * @author vignesh
 * @date 03-Apr-2014
 * @time 6:28:28 pm
 */
package org.gvignesh.initiate;

import org.gvignesh.encryption.JCEncryption;
import org.gvignesh.encryption.exception.NoKeyFoundException;
import org.springframework.stereotype.Component;

/**
 * @author vignesh
 *
 */
@Component("initiate")
public class Initiate {
	
	/**
	 * This test method initate the encryption process
	 *@return null 
	 */
	public void start(){
		JCEncryption jc = JCEncryption.getInstance("/home/vignesh/org-gvignesh.jck");
		try {
			String encryptedText = jc.encryptString("0000000000000000", "Encrypt this message");
			System.out.println(encryptedText);
			
			String decrypText = jc.decryptString("0000000000000000", encryptedText);
			System.out.println(decrypText);
		} catch (NoKeyFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}