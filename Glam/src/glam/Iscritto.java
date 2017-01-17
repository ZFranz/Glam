package glam;

import java.util.Date;

public class Iscritto {
	String nickname = "";
	String data;

	public Iscritto(String nickname, String data) {
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Iscritto [nickname=" + nickname + ", data=" + data + "]";
	}
}
