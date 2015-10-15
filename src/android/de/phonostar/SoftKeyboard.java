package de.phonostar;

import org.json.JSONArray;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

public class SoftKeyboard extends CordovaPlugin {

    public SoftKeyboard(DroidGap gap, WebView view)
    {
        mAppView = view;
        mGap = gap;
    }
    public void showKeyBoard() {
        InputMethodManager mgr = (InputMethodManager) mGap.getSystemService(Context.INPUT_METHOD_SERVICE);
        // only will trigger it if no physical keyboard is open
        mgr.showSoftInput(mAppView, InputMethodManager.SHOW_IMPLICIT);
        
        ((InputMethodManager) mGap.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(mAppView, 0);
        
    }
    
    public void hideKeyBoard() {
        InputMethodManager mgr = (InputMethodManager) mGap.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(mAppView.getWindowToken(), 0);
    }

    public boolean isKeyBoardShowing() {
      int heightDiff = this.cordova.getActivity().getWindow().getDecorView().getRootView().getHeight() - this.cordova.getActivity().getWindow().getDecorView().getHeight();
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

