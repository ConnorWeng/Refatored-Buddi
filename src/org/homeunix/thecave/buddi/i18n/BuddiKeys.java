/*
 * Created on Jun 2, 2006 by wyatt
 */
package org.homeunix.thecave.buddi.i18n;

/**
 * I use this list of keys for the Translate class.  Using this instead 
 * of string constants gives me more flexibility and less ugly code
 * that I have to wade through. 
 * @author wyatt
 *
 */
public enum BuddiKeys {
	FILECHOOSER_EXPORT_FILE_TITLE,
	FILECHOOSER_IMPORT_FILE_TITLE,
	FILE_DESCRIPTION_BUDDI_EXPORT_FILES,
	FILE_DESCRIPTION_BUDDI_IMPORT_FILES,
	BUDGET_PERIOD_WEEK,
	BUDGET_PERIOD_FORTNIGHT,
	BUDGET_PERIOD_MONTH,
	BUDGET_PERIOD_QUARTER,
	BUDGET_PERIOD_YEAR,
	IMPORT_LEGACY_BUDDI_FORMAT,
	SHOW_BUDGET_CATEGORIES_FOR_PERIOD,
	FILE_DESCRIPTION_BUDDI_PLUGINS,
	MY_REPORTS,
    NETWORK,
    SCHEDULE_NAME,
	ACCOUNT,
    ACCOUNT_AND_CATEGORY_SUMMARY,
    ACCOUNT_MODIFY_EDIT,
    ACCOUNT_MODIFY_NEW,
    ACCOUNT_TYPE,
    ACTUAL,
    ACTUAL_VS_BUDGETED_EXPENSES_TITLE,
    ADVANCED,
    ALL,
    AMOUNT,
    AND_REPEATING_EVERY,
    AND_REPEATING_ON_THE,
    AUTO,
    AVAILABLE_CREDIT,
    AVAILABLE_FUNDS,
    AVERAGE,
    BONUS,
    BUDDI,
    BUDGETED,
    BUDGETED_AMOUNT,
    BUDGET_CATEGORY,
    BUDGET_INTERVAL,
    BUDGET_NET_INCOME,
    CANCELLED_FILE_LOAD,
    CANCELLED_FILE_LOAD_MESSAGE,
    CANCELLED_FILE_RESTORE,
    CANCEL_FILE_RESTORE_MESSAGE,
    CASH,
    CATEGORY,
    CATEGORY_MODIFY_EDIT,
    CATEGORY_MODIFY_NEW,
    CHECK_CONSOLE,
    CHEQUING,
    CHOOSE_BACKUP_FILE,
    CHOOSE_DATE_INTERVAL,
    CHOOSE_EXPORT_FILE,
    CHOOSE_PLUGIN_JAR,
    CLEAR,
    CLEAR_TRANSACTION,
    CLEAR_TRANSACTION_LOSE_CHANGES,
    CLOSE_DATA_FILE,
    CONFIRM_LOAD_BACKUP_FILE,
    CONFIRM_RESTORE_BACKUP_FILE,
    CREATE_NEW_DATA_FILE,
    CREDIT,
    CREDIT_CARD,
    CREDIT_LIMIT,
    
    DATE,
    
    DEFAULT_SEARCH,
    DELETE_CATEGORY,
    DELETE_TRANSACTION,
    DELETE_TRANSACTION_LOSE_CHANGES,
    DESCRIPTION,
    DIFFERENCE,
    DOCUMENTS,
    EDIT_ACCOUNT_TYPES,
    EDIT_CATEGORY,
    ENABLE_UPDATE_NOTIFICATIONS,
    ENCRYPT_DATA_FILE_TITLE,
    ENCRYPT_DATA_FILE_YES_NO,
    ENTERTAINMENT, 
    ENTER_ACCOUNT_NAME_AND_TYPE,
    ENTER_ACCOUNT_TYPE_NAME,
    ENTER_CATEGORY_NAME,
    ERROR,
    EXPENSES,
    EXPORT_TO_CSV,
    EXPORT_TO_HTML,
    FILECHOOSER_NEW_DATA_FILE_TITLE, 
    FILECHOOSER_OPEN_DATA_FILE_TITLE,
    FILECHOOSER_SAVE_DATA_FILE_TITLE,
    FILE_DESCRIPTION_BUDDI_DATA_FILES,
    FILE_DESCRIPTION_BUDDI_BACKUP,
    FILE_SAVED,
    GRAPHS,
    GRAPH_DESCRIPTION_EXPENSE_ACTUAL_BUDGET,
    GRAPH_DESCRIPTION_EXPENSE_PIE_GRAPH,
    GRAPH_DESCRIPTION_INCOME_PIE_GRAPH,
    GRAPH_DESCRIPTION_NET_WORTH_BREAKDOWN,
    GRAPH_TITLE_EXPENSE_ACTUAL_BUDGET,
    GRAPH_TITLE_EXPENSE_PIE_GRAPH,
    GRAPH_TITLE_INCOME_PIE_GRAPH,
    GRAPH_TITLE_NET_WORTH_BREAKDOWN,
    GRAPH_TITLE_NET_WORTH_OVER_TIME,
    GROCERIES,
    HINT_CONFIRM_PASSWORD,
    HINT_DESCRIPTION,
    HINT_MEMO,
    HINT_MESSAGE,
    HINT_NUMBER,
    HINT_NAME,
    HINT_NOTES,
    HINT_PASSWORD,
    HOUSEHOLD,
    HTML_TO,
    INCOME,
    INCORRECT_PASSWORD,
    INCORRECT_PASSWORD_TITLE,
    INTEREST_RATE,
    INTERVAL_FORTNIGHT,
    INTERVAL_MONTH,
    INTERVAL_QUARTER,
    INTERVAL_WEEK,
    INTERVAL_YEAR,
    INVESTMENT,
    INVESTMENT_EXPENSES, 
    INVESTMENT_INCOME,
    JAR_FILES,
    
    LANGUAGE_EDITOR_BLANK_VALUE,
    LANGUAGE_EDITOR_HELP,
    LANGUAGE_EDITOR_LOCALE,
    LANGUAGE_EDITOR_NAME,
    LIABILITY,
    LICENCES,
    LINE_OF_CREDIT,
    LOAN,
    LOCALE,
    LOCALE_COUNTRY_CODE,
    LOCALE_LANGUAGE_CODE,

    MISC_EXPENSES,
    MISSING_DATA_FILE,
    MORE_INFO_NEEDED,
    MUST_SELECT_BUDDI_FILE,
    MY_ACCOUNTS,
    MY_BUDGET,
    NAME,
    NAME_MUST_BE_UNIQUE,
    NETWORTH_LINE_GRAPH,
    NET_WORTH,
    NEW_CATEGORY,
    NEW_TRANSACTION,
    NEW_VERSION,
    NEW_VERSION_MESSAGE,
    NEW_VERSION_MESSAGE_2,
    NOTHING_TO_PRINT,
    NOT_CLEARED,
    NOT_RECONCILED,
    NO_PARENT,
    NO_PLUGINS_IN_JAR,
    NO_PLUGINS_IN_JAR_TITLE,
    NUMBER_OF_BACKUPS,
    OF_EACH_MONTH,
    ON_EACH_OF_THE_FOLLOWING_MONTHS,
    ON_EACH_OF_THE_FOLLOWING_WEEKS,
    OPEN,
    OPENED_FILE,
    OPEN_DATA_FILE_TITLE,
    OPTIONAL_TAG,
    OVERDRAFT_LIMIT,
    OVERWRITE_EXISTING_FILE,
    OVERWRITE_EXISTING_FILE_MESSAGE,
    PARENT_CATEGORY,
    PLUGINS,
    PREFERENCES,
    PREPAID_ACCOUNT,
    PRINT_ERROR,
    PROBLEM_READING_PREFS_FILE,
    RECORD_BUTTON_ERROR,
    REPEAT_THIS_ACTION,
    REPORTS,
    REPORT_AS_OF_DATE,
    REPORT_BETWEEN,
    REPORT_BY_CATEGORY_HEADER,
    REPORT_BY_DESCRIPTION_HEADER,
    REPORT_DATE_ERROR,
    REPORT_DESCRIPTION_AVERAGE_INCOME_AND_EXPENSES_BY_CATEGORY,
    REPORT_DESCRIPTION_INCOME_EXPENSES_BY_CATEGORY,
    REPORT_DESCRIPTION_INCOME_EXPENSES_BY_DESCRIPTION,
    REPORT_DESCRIPTION_TRANSACTIONS_NOT_CLEARED,
    REPORT_DESCRIPTION_TRANSACTIONS_NOT_RECONCILED,
    REPORT_DETAILS,
    REPORT_ERROR_ADVANCED_DISPLAY_NOT_ENABLED,
    REPORT_SUMMARY,
    REPORT_TITLE_AVERAGE_INCOME_AND_EXPENSES_BY_CATEGORY,
    REPORT_TITLE_INCOME_AND_EXPENSES_BY_CATEGORY,
    REPORT_TITLE_INCOME_AND_EXPENSES_BY_DESCRIPTION,
    REPORT_TITLE_TRANSACTIONS_NOT_CLEARED,
    REPORT_TITLE_TRANSACTIONS_NOT_RECONCILED,
    REPORT_TRANSACTIONS_NOT_CLEARED,
    REPORT_TRANSACTIONS_NOT_RECONCILED,
    RESTART_NEEDED,
    RESTART_NEEDED_TITLE,
    RESTORED_FILE,
    RESTORE_DATA_FILE,
    SALARY,
    SAVINGS, 
    SCHEDULED_ACTION,
    SCHEDULED_ACTIONS,
    SCHEDULED_ACTION_NAME,
    SCHEDULED_MESSAGE,
    SCHEDULED_NOT_ENOUGH_INFO,
    SCHEDULED_NOT_ENOUGH_INFO_TITLE,




    SHORT_CLEARED,
    SHORT_RECONCILED,
    SOURCE_TO_FROM,
    STARTING_BALANCE,
    STARTING_ON,
    START_DATE_AFTER_END_DATE,
    START_DATE_IN_THE_PAST,
    START_DATE_IN_THE_PAST_TITLE,
    SUCCESSFUL_BACKUP,
    SUCCESSFUL_EXPORT,
    SUCCESSFUL_OPEN_FILE,
    SUCCESSFUL_RESTORE_FILE,
    SUCCESSFUL_SAVE_FILE,
    TO,
    TOOLTIP_AMOUNT,
    TOOLTIP_AVAILABLE_FUNDS,
    TOOLTIP_CLEARED,
    TOOLTIP_DATE, 
    TOOLTIP_DESC,
    TOOLTIP_FROM,
    TOOLTIP_MEMO,
    TOOLTIP_NUMBER,
    TOOLTIP_RECONCILED,
    TOOLTIP_SCHEDULED_MESSAGE,
    TOOLTIP_SHOW_ACCOUNT_TYPES,
    TOOLTIP_SHOW_CLEAR,
    TOOLTIP_SHOW_RECONCILE,
    TOOLTIP_SHOW_CREDIT_LIMIT,
    TOOLTIP_SHOW_INTEREST_RATE,
    TOOLTIP_TO,
    TOTAL,
    TRANSACTIONS,
    TRANSACTION_CHANGED_INVALID_MESSAGE,
    TRANSACTION_CHANGED_MESSAGE,
    TRANSACTION_CHANGED_TITLE,
    TRANSACTION_FILTER,

    TRANSACTION_FILTER_NOT_CLEARED,
    TRANSACTION_FILTER_NOT_RECONCILED,

    TYPE_MODIFY_EDIT,
    TYPE_MODIFY_NEW,
    UTILITIES,
    VIEW, 
}
