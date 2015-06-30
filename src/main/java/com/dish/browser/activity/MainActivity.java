package com.dish.browser.activity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.dish.browser.preference.PreferenceManager;
import com.dish.browser.R;

@SuppressWarnings("deprecation")
public class MainActivity extends BrowserActivity {

	CookieManager mCookieManager;

	//rickyTest
	// DownloadManager manager;
	// DownloadCompleteReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//ricky test
		// manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
		// receiver = new DownloadCompleteReceiver();
		// registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
	}

	@Override
	public void updateCookiePreference() {
		mCookieManager = CookieManager.getInstance();
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			CookieSyncManager.createInstance(this);
		}
		mCookieManager.setAcceptCookie(PreferenceManager.getInstance().getCookiesEnabled());
		super.updateCookiePreference();
	}

	@Override
	public synchronized void initializeTabs() {
		restoreOrNewTab();
		// if incognito mode use newTab(null, true); instead
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		handleNewIntent(intent);
		super.onNewIntent(intent);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveOpenTabs();
	}

	@Override
	public void updateHistory(String title, String url) {
		super.updateHistory(title, url);
		addItemToHistory(title, url);
	}

	@Override
	public boolean isIncognito() {
		return false;
	}

	@Override
	public int getMenu() {
		return R.menu.main;
	}

	@Override
	public void closeActivity() {
		closeDrawers();
		moveTaskToBack(true);
	}
	//ricky test
	// @Override
	// protected void onDestroy(){
		// //ricky test
		// if (receiver != null) {
			// unregisterReceiver(receiver);
		// }
		// super.onDestroy();
	// }

	// class DownloadCompleteReceiver extends BroadcastReceiver {

		// @Override
		// public void onReceive(Context context, Intent intent) {
            // Log.d("ricky", "onReceive: DownloadCompleteReceiver");
			// if (intent.getAction().equals(
					// DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
				// Log.d("ricky", "download completed!");
				// long downId = intent.getLongExtra(
						// DownloadManager.EXTRA_DOWNLOAD_ID, -1);

				// Log.d("ricky", "install path: " + manager.getUriForDownloadedFile(downId).toString());
				// installAPK(manager.getUriForDownloadedFile(downId));


			// }
		// }

		// private void installAPK(Uri apk) {

			// Intent intent = new Intent();
			// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// intent.setAction(android.content.Intent.ACTION_VIEW);
			// intent.setDataAndType(apk, "application/vnd.android.package-archive");
			// startActivity(intent);

		// }

	// }
}
