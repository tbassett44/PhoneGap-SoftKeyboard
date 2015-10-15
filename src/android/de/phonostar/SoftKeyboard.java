package de.phonostar;

import org.json.JSONArray;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

public class SoftKeyboard extends CordovaPlugin {

    public SoftKeyboard() {
    }

    public void showKeyBoard() {
        cordova.runOnUiThread(new Runnable() {
            public void run() {
                InputMethodManager imm = (InputMethodManager) cordova.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
            }
        });
    }

    public void hideKeyBoard() {
        cordova.runOnUiThread(new Runnable() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) cordova.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(cordova.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }

    public boolean isKeyBoardShowing() {
      int heightDiff = CordovaWebView.getRootView().getHeight() - CordovaWebView.getHeight();
      return (100 < heightDiff); // if more than 100 pixels, its probably a keyboard...
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
    if (action.equals("show")) {
      this.showKeyBoard();
      callbackContext.success("done");
      return true;
    }
    else if (action.equals("hide")) {
      this.hideKeyBoard();
      callbackContext.success();
      return true;
    }
    else if (action.equals("isShowing")) {
      callbackContext.success(Boolean.toString(this.isKeyBoardShowing()));
      return true;
    }
    else {
      return false;
    }
  }
}

