/**
 * KeystoreUtil.java
 * com.piqube.jcencryption
 * @author vignesh
 * @date 28-Apr-2014
 * @time 7:23:21 pm
 */
package org.gvignesh.jcencryption;

import com.google.common.io.BaseEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * @author vignesh
 *
 */
public class KeystoreUtil {

    private static final Logger LOG = LoggerFactory.getLogger(KeystoreUtil.class);

    /**
     * @param keystoreLocation
     * @param keystorePass
     * @param alias
     * @param keyPass
     * @return
     */
    public static Key getKeyFromKeyStore(final String keystoreLocation, final String keystorePass, final String alias, final String keyPass) {
        try {

            InputStream keystoreStream = new FileInputStream(keystoreLocation);

            KeyStore keystore = KeyStore.getInstance("JCEKS");

            keystore.load(keystoreStream, keystorePass.toCharArray());

            LOG.debug("Keystore with alias {} found == {}", alias, keystore.containsAlias(alias));
            if (!keystore.containsAlias(alias)) {
                throw new RuntimeException("Alias for key not found");
            }
            
            Key key = keystore.getKey(alias, keyPass.toCharArray());
            LOG.debug("Key Found {} -> {}", key.getAlgorithm(), BaseEncoding.base64().encode(key.getEncoded()));

            return key;

        } catch (UnrecoverableKeyException | KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException e) {
        	e.printStackTrace();
        	throw new RuntimeException(e);
        }
    }
}