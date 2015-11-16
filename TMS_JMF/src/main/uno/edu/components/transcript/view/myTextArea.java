package main.uno.edu.components.transcript.view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.LayeredHighlighter;
import javax.swing.text.Position;
import javax.swing.text.View;

import main.uno.edu.components.transcript.view.BackGroundHighLighter.BackGroundHighlighterPainter;

public class myTextArea extends JTextPane implements MouseListener,ActionListener {
	protected Highlighter.HighlightPainter painter;

	public myTextArea(String data) {
		setText(data);
		setEditable(false);
		setHighlighter(new BackGroundHighLighter(Color.red));

	}

	public myTextArea(String data, List<String> bookmarkedList) {
		setText(data);
		setEditable(false);
		// this should highlight the bookmarked text in the second phase

		Highlighter h = this.getHighlighter();
		BackGroundHighlighterPainter painter1 = new BackGroundHighLighter.BackGroundHighlighterPainter(
				Color.red);
		String highlightWord = "life";
		highLightWithWord(highlightWord, painter1);

	}

	public void highLightWithWord(String word,
			BackGroundHighlighterPainter painter1) {
		String content = null;
		int lastIndex = -1;
		try {
			Document d = this.getDocument();
			content = d.getText(0, d.getLength()).toLowerCase();
		} catch (BadLocationException e) {
			// Cannot happen

		}

		word = word.toLowerCase();

		lastIndex = content.indexOf(word);
		int endIndex = lastIndex + word.length();
		doit(lastIndex, endIndex, painter1);
	}

	public void doit(int p0, int p1, BackGroundHighlighterPainter painter) {
		testhightlight(p0, p1, painter);
		try {
			this.scrollRectToVisible(this.modelToView(p0));
		} catch (BadLocationException e) {

		}

	}

	public void testhightlight(int p0, int p1,
			BackGroundHighlighterPainter painter) {
		Highlighter highlighter = this.getHighlighter();

		try {
			highlighter.addHighlight(p0, p1, painter);

		} catch (BadLocationException e) {
			// Nothing to do
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Highlighter h = this.getHighlighter();
	    h.removeAllHighlights();
	    String text = this.getText().toUpperCase();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

class BackGroundHighLighter extends DefaultHighlighter {
	protected Highlighter.HighlightPainter painter;
	// Shared painter used for default highlighting
	protected static final Highlighter.HighlightPainter sharedPainter = new BackGroundHighlighterPainter(
			null);

	public BackGroundHighLighter(Color c) {
		painter = (c == null ? sharedPainter
				: new BackGroundHighlighterPainter(c));
	}

	// Convenience method to add a highlight with
	// the default painter.
	public Object addHighlight(int p0, int p1) throws BadLocationException {
		return addHighlight(p0, p1, painter);
	}

	// Painter for background highlights
	public static class BackGroundHighlighterPainter extends
			LayeredHighlighter.LayerPainter {
		public BackGroundHighlighterPainter(Color c) {
			color = c;
		}

		public void paint(Graphics g, int offs0, int offs1, Shape bounds,
				JTextComponent c) {
			// Do nothing: this method will never be called
		}

		public Shape paintLayer(Graphics g, int offs0, int offs1, Shape bounds,
				JTextComponent c, View view) {
			g.setColor(color == null ? c.getSelectionColor() : color);

			Rectangle alloc = null;
			if (offs0 == view.getStartOffset() && offs1 == view.getEndOffset()) {
				if (bounds instanceof Rectangle) {
					alloc = (Rectangle) bounds;
				} else {
					alloc = bounds.getBounds();
				}
			} else {
				try {
					Shape shape = view.modelToView(offs0,
							Position.Bias.Forward, offs1,
							Position.Bias.Backward, bounds);
					alloc = (shape instanceof Rectangle) ? (Rectangle) shape
							: shape.getBounds();
				} catch (BadLocationException e) {
					return null;
				}
			}

			FontMetrics fm = c.getFontMetrics(c.getFont());
			int baseline = alloc.y + alloc.height - fm.getDescent() + 1;
			g.drawLine(alloc.x, baseline, alloc.x + alloc.width, baseline);
			g.drawLine(alloc.x, baseline + 1, alloc.x + alloc.width,
					baseline + 1);

			return alloc;
		}

		protected Color color; // The color for the underline
	}

}
