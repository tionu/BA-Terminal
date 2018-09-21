package de.htwg.gib.egkterminal.model;

public class CipherObject {

	private String ciphertext;
	private String nonce;

	public CipherObject(String ciphertext, String nonce) {
		this.ciphertext = ciphertext;
		this.nonce = nonce;
	}

	public String getCiphertext() {
		return ciphertext;
	}

	public void setCiphertext(String ciphertext) {
		this.ciphertext = ciphertext;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

}
