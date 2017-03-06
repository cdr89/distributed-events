package it.caldesi.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

	@SerializedName("Account Number")
	@Expose
	private String accountNumber;
	@SerializedName("Transaction Amount")
	@Expose
	private String transactionAmount;
	@SerializedName("Name")
	@Expose
	private String name;
	@SerializedName("Product")
	@Expose
	private String product;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

}
