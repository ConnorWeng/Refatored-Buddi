/*
 * Created on May 14, 2006 by wyatt
 */
package org.homeunix.drummer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.homeunix.drummer.Const;
import org.homeunix.drummer.prefs.Interval;
import org.homeunix.drummer.prefs.PrefsInstance;
import org.homeunix.drummer.util.Formatter;
import org.homeunix.drummer.util.Log;
import org.homeunix.drummer.view.AbstractDialog;
import org.homeunix.drummer.view.PreferencesDialogLayout;

public class PreferencesFrame extends PreferencesDialogLayout {
	public static final long serialVersionUID = 0;

	public PreferencesFrame(){
		super(MainBuddiFrame.getInstance());
	}
		
	@Override
	protected AbstractDialog initActions() {
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				boolean needRestart = false;
				if (!PrefsInstance.getInstance().getPrefs().getLanguage().equals(language.getSelectedItem().toString())){
				
					Translate.getInstance().loadLanguage(language.getSelectedItem().toString());
					needRestart = true;
				}

				if (numberOfBackups.getSelectedItem() instanceof Integer)
					PrefsInstance.getInstance().getPrefs().setNumberOfBackups((Integer) numberOfBackups.getSelectedItem());
				else //If there is some problem, at least make sure user has some backups
					PrefsInstance.getInstance().getPrefs().setNumberOfBackups(10);
				PrefsInstance.getInstance().getPrefs().setLanguage(language.getSelectedItem().toString());
				PrefsInstance.getInstance().getPrefs().setDateFormat(dateFormat.getSelectedItem().toString());
				PrefsInstance.getInstance().getPrefs().setCurrencySymbol(currencyFormat.getSelectedItem().toString());
				if (budgetInterval.getSelectedItem() instanceof Interval)
					PrefsInstance.getInstance().getPrefs().setSelectedInterval(((Interval) budgetInterval.getSelectedItem()).getName());
				else
					if (Const.DEVEL) Log.debug("Unknown type (should be Interval): " + budgetInterval.getSelectedItem());
				PrefsInstance.getInstance().getPrefs().setShowDeletedAccounts(showDeletedAccounts.isSelected());
				PrefsInstance.getInstance().getPrefs().setShowDeletedCategories(showDeletedCategories.isSelected());
				PrefsInstance.getInstance().getPrefs().setShowAccountTypes(showAccountTypes.isSelected());
				PrefsInstance.getInstance().getPrefs().setShowAutoComplete(showAutoComplete.isSelected());
				PrefsInstance.getInstance().getPrefs().setShowCreditLimit(showCreditLimit.isSelected());
				PrefsInstance.getInstance().getPrefs().setShowInterestRate(showInterestRate.isSelected());
				PrefsInstance.getInstance().getPrefs().setEnableUpdateNotifications(enableUpdateNotifications.isSelected());
				PrefsInstance.getInstance().getPrefs().setShowAdvanced(showClearReconcile.isSelected());				
				PrefsInstance.getInstance().savePrefs();
												
				Formatter.getInstance().reloadDateFormat();				
				
				if (needRestart){
					int retValue = JOptionPane.showConfirmDialog(
							PreferencesFrame.this,
							Translate.getInstance().get(TranslateKeys.RESTART_NEEDED),
							Translate.getInstance().get(TranslateKeys.RESTART_NEEDED_TITLE),
							JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE
					);
					
					if (retValue == JOptionPane.YES_OPTION){
						if (MainBuddiFrame.getInstance() != null)
							MainBuddiFrame.getInstance().savePosition();

						MainBuddiFrame.restartProgram();
					}
				}
				else {
					MainBuddiFrame.getInstance().getAccountListPanel().updateContent();
					MainBuddiFrame.getInstance().getCategoryListPanel().updateContent();
				}
				TransactionsFrame.updateAllTransactionWindows();
				
				PreferencesFrame.this.setVisible(false);
				PreferencesFrame.this.dispose();
			}
		});
		
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				PreferencesFrame.this.setVisible(false);
				
				MainBuddiFrame.getInstance().getAccountListPanel().updateContent();
				MainBuddiFrame.getInstance().getCategoryListPanel().updateContent();
			}
		});
		
		return this;
	}

	@Override
	protected AbstractDialog initContent() {
		updateContent();

		language.setSelectedItem(PrefsInstance.getInstance().getPrefs().getLanguage());
		dateFormat.setSelectedItem(PrefsInstance.getInstance().getPrefs().getDateFormat());
		currencyFormat.setSelectedItem(PrefsInstance.getInstance().getPrefs().getCurrencySymbol());

		
		showDeletedAccounts.setSelected(PrefsInstance.getInstance().getPrefs().isShowDeletedAccounts());
		showDeletedCategories.setSelected(PrefsInstance.getInstance().getPrefs().isShowDeletedCategories());
		showAccountTypes.setSelected(PrefsInstance.getInstance().getPrefs().isShowAccountTypes());
		showAutoComplete.setSelected(PrefsInstance.getInstance().getPrefs().isShowAutoComplete());
		showCreditLimit.setSelected(PrefsInstance.getInstance().getPrefs().isShowCreditLimit());
		showInterestRate.setSelected(PrefsInstance.getInstance().getPrefs().isShowInterestRate());
		showClearReconcile.setSelected(PrefsInstance.getInstance().getPrefs().isShowAdvanced());

		budgetInterval.setSelectedItem(PrefsInstance.getInstance().getSelectedInterval());
		numberOfBackups.setSelectedItem(PrefsInstance.getInstance().getPrefs().getNumberOfBackups());
		enableUpdateNotifications.setSelected(PrefsInstance.getInstance().getPrefs().isEnableUpdateNotifications());
		
		return this;
	}

	public AbstractDialog updateContent(){
		languageModel.removeAllElements();

		Set<String> languages = new HashSet<String>();
		
		// Load all available languages into Prefs.  Start with 
		// the bundled languages, and if there are more, load them too.
		for (String language : Const.BUNDLED_LANGUAGES) {
			languages.add(language);			
		}
		
		File languageLocation = new File(Const.LANGUAGE_FOLDER);
		if (languageLocation.exists() && languageLocation.isDirectory()){
			for (File f: languageLocation.listFiles())
				if (f.getName().endsWith(Const.LANGUAGE_EXTENSION))
					languages.add(f.getName().replaceAll(Const.LANGUAGE_EXTENSION, ""));
		}
		else{
			Log.critical("Cannot find language directory");
		}

		Vector<String> languagesVector = new Vector<String>(languages);
		Collections.sort(languagesVector);
		for (String string : languagesVector) {
			languageModel.addElement(string);
		}
		
		return this;
	}


}
