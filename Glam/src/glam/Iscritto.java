package glam;

public class Iscritto {
	String nickname = "";

	public Iscritto(String nickname) {
		super();
		this.nickname = nickname;
	}

	public String getNome() {
		return nickname;
	}

	public void setNome(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "Iscritto [nickname=" + nickname + "]";
	}
}
