package snakefarm;

import java.awt.Graphics;

/**
 *
 * @author cassus
 */
public class FieldView extends BaseView {
	Field field;

	public FieldView(Field field) {
		this.field = field;
	}

	@Override
	public void paint(Graphics g) {
		Viewable viewable = field.getViewable();
		if (viewable != null) {
			viewable.getBaseView().paint(g);
		}
	}

}
