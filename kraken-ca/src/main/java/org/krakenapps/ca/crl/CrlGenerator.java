package org.krakenapps.ca.crl;

import java.security.PrivateKey;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;

import org.bouncycastle.x509.X509V2CRLGenerator;

public class CrlGenerator {
	private PrivateKey caPrivateKey;
	private X509Certificate caCert;

	public CrlGenerator(PrivateKey caPrivateKey, X509Certificate caCert) {
		this.caPrivateKey = caPrivateKey;
		this.caCert = caCert;
	}

	@SuppressWarnings("deprecation")
	public byte[] getCrl() throws Exception {
		X509V2CRLGenerator generator = new X509V2CRLGenerator();
		generator.setIssuerDN(caCert.getIssuerX500Principal());

		generator.setThisUpdate(new Date());
		generator.setSignatureAlgorithm(caCert.getSigAlgName());

		RevokedCertificatesManager manager = new RevokedCertificatesManager();
		List<RevokedCertificate> l = manager.getRevokedCertifcates();
		for(RevokedCertificate rc: l)
			generator.addCRLEntry(rc.getSerialNumber(), rc.getRevocationDate(), rc.getReasonCode());
		X509CRL crl = generator.generate(caPrivateKey);
		return crl.getEncoded();
	}
}