/*
 * Created on May 6, 2006 by wyatt
 */
package org.homeunix.drummer.view.reports.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.homeunix.drummer.Buddi;
import org.homeunix.drummer.Strings;
import org.homeunix.drummer.view.AbstractBudgetFrame;

public abstract class ReportFrameLayout extends AbstractBudgetFrame {
	public static final long serialVersionUID = 0;

	protected final JLabel reportLabel;
	protected final JButton okButton;
	
	public ReportFrameLayout(Date startDate, Date endDate){
		reportLabel = new JLabel();
		okButton = new JButton(Strings.inst().get(Strings.OK));
		
		Dimension buttonSize = new Dimension(100, okButton.getPreferredSize().height);
		okButton.setPreferredSize(buttonSize);
		
		reportLabel.setBackground(Color.WHITE);
						
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(okButton);
		
		JScrollPane reportLabelScroller = new JScrollPane(reportLabel);
		reportLabelScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);				
		
		JPanel reportPanelSpacer = new JPanel(new BorderLayout());
		reportPanelSpacer.setBorder(BorderFactory.createEmptyBorder(7, 17, 17, 17));
		reportPanelSpacer.add(reportLabelScroller, BorderLayout.CENTER);
		
		JPanel reportPanel = new JPanel(new BorderLayout());
		reportPanel.setBorder(BorderFactory.createTitledBorder(""));
		reportPanel.add(reportPanelSpacer, BorderLayout.CENTER);
		
		JPanel mainPanel = new JPanel(); 
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(7, 17, 17, 17));
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(reportPanel, BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		
		if (Buddi.isMac()){
			reportLabelScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		
		reportLabel.setText(buildReport(startDate, endDate));
		
		reportLabel.setPreferredSize(new Dimension(reportLabel.getPreferredSize().width, Math.min(400, reportLabel.getPreferredSize().height)));
		
		//Call the method to add actions to the buttons
		initActions();
		
		//Show the window
		openWindow();
	}
	
	protected abstract String buildReport(Date startDate, Date endDate);
	
	@Override
	public Component getPrintedComponent() {
		return reportLabel;
	}
}