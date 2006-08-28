/*
 * Created on May 19, 2006 by wyatt
 */
package org.homeunix.drummer.controller.layout.reports;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

import org.homeunix.drummer.Buddi;
import org.homeunix.drummer.Translate;
import org.homeunix.drummer.TranslateKeys;
import org.homeunix.drummer.controller.TransactionsFrame;
import org.homeunix.drummer.controller.model.DataInstance;
import org.homeunix.drummer.controller.model.PrefsInstance;
import org.homeunix.drummer.model.Account;
import org.homeunix.drummer.model.Category;
import org.homeunix.drummer.model.Transaction;
import org.homeunix.drummer.prefs.Interval;
import org.homeunix.drummer.util.DateUtil;
import org.homeunix.drummer.util.Formatter;
import org.homeunix.drummer.util.Log;
import org.homeunix.drummer.view.AbstractBudgetFrame;
import org.homeunix.drummer.view.ReportFrameLayout;

public class IncomeExpenseByCategoryReportFrame extends ReportFrameLayout {
	public static final long serialVersionUID = 0;
	
	public IncomeExpenseByCategoryReportFrame(Date startDate, Date endDate){
		super(startDate, endDate);		
	}
	
	@Override
	protected TreeModel buildReport(Date startDate, Date endDate) {
		Vector<Transaction> transactions = DataInstance.getInstance().getTransactions(startDate, endDate);
		Map<Category, Long> categories = new HashMap<Category, Long>();
		
		//This map is where we store the totals for this time period.
		for (Category category : DataInstance.getInstance().getCategories()) {
			categories.put(category, new Long(0));
		}
		
		for (Transaction transaction : transactions) {
			//Sum up the amounts for each category.
			if (transaction.getFrom() instanceof Category){
				Category c = (Category) transaction.getFrom();
				Long l = categories.get(c);
				l += transaction.getAmount();
				categories.put(c, l);
				Log.debug("Added a source");
			}
			else if (transaction.getTo() instanceof Category){
				Category c = (Category) transaction.getTo();
				Long l = categories.get(c);
				l += transaction.getAmount();
				categories.put(c, l);
				Log.debug("Added a destination");
			}
			else
				Log.debug("Didn't add anything...");
		}
		
		//Print the results
//		StringBuffer sb = new StringBuffer("<html><body><table>");
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		TreeModel model = new DefaultTreeModel(root);
		
		Vector<Category> cats = new Vector<Category>(categories.keySet());
		Collections.sort(cats);
				
		long numberOfBudgetPeriods;
		Interval interval = PrefsInstance.getInstance().getSelectedInterval();
		if (!interval.isDays()){
			numberOfBudgetPeriods = (DateUtil.monthsBetween(startDate, endDate) + 1) / interval.getLength();
		}
		else{
			numberOfBudgetPeriods = (DateUtil.daysBetween(startDate, endDate) + 1) / interval.getLength();
		}
			
		long total = 0;
		
		for (Category c : cats) {
			if (c.getBudgetedAmount() > 0 || categories.get(c) > 0){
				long budgeted = c.getBudgetedAmount() * numberOfBudgetPeriods;
				long actual = categories.get(c);
				long difference = (budgeted - actual);
				if (c.isIncome())
					total += actual;
				else
					total -= actual;
				IncomeExpenseReportEntry entry = new IncomeExpenseReportEntry(c, budgeted, actual, difference);
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(entry);
				root.add(node);
				
				//Add each transaction as a subitem of the total.
				Vector<Transaction> t = DataInstance.getInstance().getTransactions(c, startDate, endDate);
				for (Transaction transaction : t) {					
					DefaultMutableTreeNode transactionNode = new DefaultMutableTreeNode(transaction);
					node.add(transactionNode);
				}
			}
		}
				
		root.add(new DefaultMutableTreeNode(null));
		ReportEntryTotal ret = new ReportEntryTotal(total);
		DefaultMutableTreeNode totalNode = new DefaultMutableTreeNode(ret);
		root.add(totalNode);
		
		return model;
	}
	
	@Override
	protected AbstractBudgetFrame initActions() {
		reportTree.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				editButton.setEnabled(selectedNode != null && selectedNode.getUserObject() instanceof Transaction);
				
				if (arg0.getClickCount() >= 2){
					editButton.doClick();
				}
				super.mouseClicked(arg0);
			}
		});
		
		editButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Object o = selectedNode.getUserObject();
				if (o instanceof Transaction){
					Transaction transaction = (Transaction) o;
					Account a;
					if (transaction.getTo() instanceof Account)
						a = (Account) transaction.getTo();
					else if (transaction.getFrom() instanceof Account)
						a = (Account) transaction.getFrom();	
					else
						a = null;
					
					if (a != null) {
						new TransactionsFrame(a, transaction);
					}
				}
			}
		});
		
		return super.initActions();
	}
	
	@Override
	protected TreeCellRenderer getTreeCellRenderer() {
		return new ReportTreeCellRenderer();
	}

	@Override
	protected AbstractBudgetFrame initContent() {
		return this;
	}

	@Override
	public AbstractBudgetFrame updateButtons() {
		return this;
	}

	@Override
	public AbstractBudgetFrame updateContent() {
		return this;
	}
	
	public class ReportEntryTotal {
		private long total;

		public ReportEntryTotal(long total){
			this.total = total;
		}
		
		public long getTotal() {
			return total;
		}

		public void setTotal(long total) {
			this.total = total;
		}
	}
	
	public class IncomeExpenseReportEntry {
		private Category category;
		private long budgeted;
		private long actual;
		private long difference;
		
		public IncomeExpenseReportEntry(Category name, long budgeted, long actual, long difference) {
			this.category = name;
			this.budgeted = budgeted;
			this.actual = actual;
			this.difference = difference;
		}
			
		public long getActual() {
			return actual;
		}
		public void setActual(long actual) {
			this.actual = actual;
		}
		public long getBudgeted() {
			return budgeted;
		}
		public void setBudgeted(long budgeted) {
			this.budgeted = budgeted;
		}
		public long getDifference() {
			return difference;
		}
		public void setDifference(long difference) {
			this.difference = difference;
		}
		public Category getCategory() {
			return category;
		}
		public void setCategory(Category name) {
			this.category = name;
		}
	}
	
	public class ReportTreeCellRenderer extends JLabel implements TreeCellRenderer {
		public static final long serialVersionUID = 0;
				
		public ReportTreeCellRenderer(){
			super();
		}
		
		public Component getTreeCellRendererComponent(JTree tree, Object node, boolean isSelected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
			Object obj;
			DefaultMutableTreeNode n;
			if (node instanceof DefaultMutableTreeNode) {
				n = (DefaultMutableTreeNode) node;
				obj = n.getUserObject();
			}
			else
				return this;
			
			this.setBorder(BorderFactory.createEmptyBorder());
			
			if (obj == null){
				this.setText("");
			}
			else if (obj instanceof IncomeExpenseReportEntry){
				IncomeExpenseReportEntry entry = (IncomeExpenseReportEntry) obj;

				long difference = (entry.getBudgeted() - entry.getActual()); 
				if (entry.getCategory().isIncome() && difference != 0)
					difference *= -1;
				
				StringBuffer sb = new StringBuffer();
				
				
				sb.append(
						"<html><table><tr><td width=130px><u>")
						.append(Translate.getInstance().get(Formatter.getInstance().getLengthFormat(25).format(entry.getCategory().toString())))
						.append("</u></td><td width=70px>")
						.append(PrefsInstance.getInstance().getPrefs().getCurrencySymbol())
						.append(Formatter.getInstance().getDecimalFormat().format(Math.abs((double) entry.getBudgeted() / 100.0)))
						.append("</td><td width=70px>")
						.append(PrefsInstance.getInstance().getPrefs().getCurrencySymbol())
						.append(Formatter.getInstance().getDecimalFormat().format(Math.abs((double) entry.getActual() / 100.0)))
						.append("</td><td width=70px>")
						.append((difference < 0 ? "<font color='red'>" : ""))
						.append(PrefsInstance.getInstance().getPrefs().getCurrencySymbol())
						.append(Formatter.getInstance().getDecimalFormat().format(Math.abs((double) entry.getDifference() / 100.0)))
						.append((difference < 0 ? "</font>" : ""))
						.append("</td></tr></table></html>");
				
				this.setText(sb.toString());
			}
			else if (obj instanceof ReportEntryTotal){
				ReportEntryTotal entry = (ReportEntryTotal) obj;
				
				StringBuffer sb = new StringBuffer();
				
				sb.append(
						"<html><table><tr><td width=270px><b>")
						.append(Translate.getInstance().get(TranslateKeys.TOTAL))
						.append("</b></td><td width=70px><b>")
						.append((entry.getTotal() < 0 ? "<font color='red'>" : ""))
						.append(PrefsInstance.getInstance().getPrefs().getCurrencySymbol())
						.append(Formatter.getInstance().getDecimalFormat().format(Math.abs((double) entry.getTotal() / 100.0)))
						.append((entry.getTotal() < 0 ? "</font>" : ""))
						.append("</b></td></tr></table></html>");
				
				this.setText(sb.toString());
			}
			else if (obj instanceof Transaction){
				Transaction transaction = (Transaction) obj;
				
				StringBuffer sb = new StringBuffer();
				
				sb.append(
						"<html><table><tr><td width=150px>")
						.append(Formatter.getInstance().getLengthFormat(25).format(transaction.getDescription()))
						.append("</td><td width=80px>")
						.append(PrefsInstance.getInstance().getPrefs().getCurrencySymbol())
						.append(Formatter.getInstance().getDecimalFormat().format(Math.abs((double) transaction.getAmount() / 100.0)))
						.append("</td><td width=200px>");
						if (transaction.getTo() instanceof Account){
							sb.append(transaction.getTo());
						}
						else{
							sb.append(transaction.getFrom());							
						}
						sb.append("</td></tr></table></html>");
				
				this.setText(sb.toString());
			}
			
			if (!Buddi.isMac()){
				if (isSelected){
					this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				}
				else{
					this.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
				}
			}

			
			return this;
		}		
	}
	
	@Override
	public String getHtmlReport() {
		StringBuffer sb = getHtmlHeader(TranslateKeys.REPORT_BY_CATEGORY_HEADER);
		
		sb.append("<table class='main'>\n");
		
		if (reportTree.getModel().getRoot() instanceof DefaultMutableTreeNode){
			DefaultMutableTreeNode root = (DefaultMutableTreeNode) reportTree.getModel().getRoot();

			sb.append(
					"<tr><th>")
					.append(Translate.getInstance().get(TranslateKeys.CATEGORY))
					.append("</th><th>")
					.append(Translate.getInstance().get(TranslateKeys.BUDGETED))
					.append("</th><th>")
					.append(Translate.getInstance().get(TranslateKeys.ACTUAL))
					.append("</th><th>")
					.append(Translate.getInstance().get(TranslateKeys.DIFFERENCE))
					.append("</th></tr>\n");
			
			for (int i = 0; i < root.getChildCount(); i++) {
				DefaultMutableTreeNode child = (DefaultMutableTreeNode) root.getChildAt(i);
				Object userObject = child.getUserObject();
				if (userObject instanceof IncomeExpenseReportEntry){
					IncomeExpenseReportEntry entry = (IncomeExpenseReportEntry) userObject;
					
					long difference = (entry.getBudgeted() - entry.getActual()); 
					if (entry.getCategory().isIncome() && difference != 0)
						difference *= -1;
					
					
					sb.append(
							"<tr><td>")
							.append(Translate.getInstance().get(entry.getCategory().toString()))
							.append("</td><td>")
							.append(PrefsInstance.getInstance().getPrefs().getCurrencySymbol())
							.append(Formatter.getInstance().getDecimalFormat().format(Math.abs((double) entry.getBudgeted() / 100.0)))
							.append("</td><td>")
							.append(PrefsInstance.getInstance().getPrefs().getCurrencySymbol())
							.append(Formatter.getInstance().getDecimalFormat().format(Math.abs((double) entry.getActual() / 100.0)))
							.append("</td>")
							.append((difference < 0 ? "<td class='red'>" : "<td>"))
							.append(PrefsInstance.getInstance().getPrefs().getCurrencySymbol())
							.append(Formatter.getInstance().getDecimalFormat().format(Math.abs((double) entry.getDifference() / 100.0)))
							.append("</td></tr>\n");
					
					if (child.getChildCount() > 0){
						sb.append("<tr><td colspan=4><table class='transactions'>");
						for (int j = 0; j < child.getChildCount(); j++) {
							sb.append("<tr>");
							DefaultMutableTreeNode subChild = (DefaultMutableTreeNode) child.getChildAt(j);
							Object subUserObject = subChild.getUserObject();
							if (subUserObject instanceof Transaction){
								Transaction t = (Transaction) subUserObject;
								sb.append(
								"<td>")
								.append(t.getDescription())
								.append("</td><td>")
								.append(PrefsInstance.getInstance().getPrefs().getCurrencySymbol())
								.append(Formatter.getInstance().getDecimalFormat().format(Math.abs((double) t.getAmount() / 100.0)))
								.append("</td><td>");
								if (t.getTo() instanceof Account){
									sb.append(t.getTo());
								}
								else{
									sb.append(t.getFrom());							
								}
								sb.append("</td>");

							}
							sb.append("</tr>");
						}
						sb.append("</table></td></tr>");
					}
				}
				else if (userObject instanceof ReportEntryTotal){
					ReportEntryTotal entry = (ReportEntryTotal) userObject;
					
					sb.append(
							"<tr><th colspan=3><b>")
							.append(Translate.getInstance().get(TranslateKeys.TOTAL))
							.append("</b></th>")
							.append((entry.getTotal() < 0 ? "<th class='red'>" : "<th>"))
							.append("<b>")
							.append(PrefsInstance.getInstance().getPrefs().getCurrencySymbol())
							.append(Formatter.getInstance().getDecimalFormat().format(Math.abs((double) entry.getTotal() / 100.0)))
							.append("</b></th>")
							.append("</tr>");
				}
			}
		}
		
		sb.append("</table>\n</body>\n</html>");
		
		return sb.toString();
	}
}