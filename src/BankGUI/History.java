package BankGUI;

public class History {
	private String hoatdong;
	private String taikhoannhan;
	private String thoigian;
	
	public History(String hoatdong, String taikhoannhan, String thoigian) {
		super();
		this.hoatdong = hoatdong;
		this.taikhoannhan = taikhoannhan;
		this.thoigian = thoigian;
	}
	
	
	public String getHoatdong() {
		return hoatdong;
	}
	public void setHoatdong(String hoatdong) {
		this.hoatdong = hoatdong;
	}
	public String getTaikhoannhan() {
		return taikhoannhan;
	}
	public void setTaikhoannhan(String taikhoannhan) {
		this.taikhoannhan = taikhoannhan;
	}
	public String getThoigian() {
		return thoigian;
	}
	public void setThoigian(String thoigian) {
		this.thoigian = thoigian;
	}
	
	
}
