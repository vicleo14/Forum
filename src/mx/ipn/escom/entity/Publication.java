package mx.ipn.escom.entity;
/*
 * 
 */
public class Publication {
	protected Integer idPub;
	protected String text;
	protected String image;
	protected String user;//Revisar la conveniencia
	
	public Publication() {}
	public Publication(Integer idPub,String user,String text,String image)
	{
		this.idPub=idPub;
		this.user=user;
		this.text=text;
		this.image=image;
	}
	public Integer getIdPub() {
		return idPub;
	}
	public void setIdPub(Integer idPub) {
		this.idPub = idPub;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	

}
