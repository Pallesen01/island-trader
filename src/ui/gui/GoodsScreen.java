package ui.gui;

import javax.swing.JFrame;

import core.GameEnvironment;
import core.Item;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Color;

public class GoodsScreen extends Screen {
	
	private JFrame frame;
	private JTable goodsTable;

	/**
	 * Create the application.
	 */
	public GoodsScreen(GameEnvironment game) {
		super(game);
		frame = new JFrame();
		initialiseFrame();
		configureFrame();
	}
	
	@Override
	JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Returns 2D array from items where each row has a name, price, size, and description.
	 * @param goods 
	 * @return 2D array of item info
	 */
	private Object[][] makeGoodArray(ArrayList<Item> goods) {
		Object goodArray[][] = new Object[goods.size()][];
		int i = 0;
		for (Item good : goods) {
			Object goodRow[] = {good.getName(), good.getPrice(), good.getSoldFor(), good.getSoldAt()};
			// Sets sold price to null if not sold
			if (good.getSoldFor() == -1) {
				goodRow[2] = null;
			}
			goodArray[i] = goodRow;
			i++;
		}
		return goodArray;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialiseFrame() {
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane goodsScrollPane = new JScrollPane();
		
		JLabel goodsLabel = new JLabel("Goods");
		goodsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		goodsLabel.setVerticalAlignment(SwingConstants.TOP);
		goodsLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(e -> getGame().getUI().menu());
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backBtn.setFocusable(false);
		backBtn.setBackground(Color.LIGHT_GRAY);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(goodsScrollPane, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
						.addComponent(goodsLabel, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
						.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(goodsLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(goodsScrollPane, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(backBtn)
					.addContainerGap())
		);
		
		goodsTable = new JTable();
		goodsTable.setRowSelectionAllowed(false);
		
		String[] columnText = {"Name", "Bought for", "Sold for", "Sold at"};
		Object[][] goodArray = makeGoodArray(getGame().getGoods());
		goodsTable.setModel(new DefaultTableModel(goodArray, columnText) {
			private static final long serialVersionUID = -156423262431076534L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		goodsScrollPane.setViewportView(goodsTable);
		frame.getContentPane().setLayout(groupLayout);
	}
}
