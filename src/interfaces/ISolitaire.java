package interfaces;

public interface ISolitaire {
	public void setPlateau(IPlateau plateau);

	public void start();

	public void undo();

	public void redo();
}
