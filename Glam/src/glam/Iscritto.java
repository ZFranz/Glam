package glam;

import java.util.Date;

public class Iscritto {
	String nickname = "";
	Date data;

	public Iscritto(String nickname, Date data) {
		super();
		this.nickname = nickname;
		this.data = data;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Iscritto [nickname=" + nickname + ", data=" + data + "]";
	}
}
