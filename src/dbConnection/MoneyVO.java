package dbConnection;

public class MoneyVO {
	private String id;
	private int money_char;
	private String char_date;
	private int balance;
	
	public MoneyVO() {}
	public MoneyVO(String id, int money_char, String char_date, int balance) {
		this.id = id;
		this.money_char = money_char;
		this.char_date = char_date;
		this.balance = balance;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMoney_char() {
		return money_char;
	}
	public void setMoney_char(int money_char) {
		this.money_char = money_char;
	}
	public String getChar_date() {
		return char_date;
	}
	public void setChar_date(String char_date) {
		this.char_date = char_date;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
}
