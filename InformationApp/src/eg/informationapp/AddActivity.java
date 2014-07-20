package eg.informationapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

/**
 * @author Prabu 
 * July 30 2013
 * @version 1.0
 * 
 */
public class AddActivity extends Activity {
 private Camera camera;
 private ToggleButton button;
 private final Context context = this;
 /**
  * @see android.app.Activity#onStop()
  */
 @Override
 protected void onStop() {
  super.onStop();
  if (camera != null) {
   camera.release();
  }
 }

 /**
  * @see android.app.Activity#onCreate(android.os.Bundle)
  */
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.flash);
  button = (ToggleButton) findViewById(R.id.togglebutton);
  
  final PackageManager pm = context.getPackageManager();
  if(!isCameraSupported(pm)){
   AlertDialog alertDialog = new AlertDialog.Builder(context).create();
   alertDialog.setTitle("No Camera");
      alertDialog.setMessage("The device's doesn't support camera.");
      alertDialog.setButton(RESULT_OK, "OK", new DialogInterface.OnClickListener() {
          public void onClick(final DialogInterface dialog, final int which) { 
           Log.e("err", "The device's doesn't support camera.");
          }
       });
   alertDialog.show();
  }
  camera = Camera.open();
 }
 public void onToggleClicked(View view) {
  PackageManager pm=context.getPackageManager();
  final Parameters p = camera.getParameters();
  if(isFlashSupported(pm)){
   boolean on = ((ToggleButton) view).isChecked();
   if (on) {
    Log.i("info", "torch is turn on!");
    p.setFlashMode(Parameters.FLASH_MODE_TORCH);
    camera.setParameters(p);
    camera.startPreview();
   } else {
    Log.i("info", "torch is turn off!");
    p.setFlashMode(Parameters.FLASH_MODE_OFF);
    camera.setParameters(p);
    camera.stopPreview();
   }
  }else{
   button.setChecked(false);
   AlertDialog alertDialog = new AlertDialog.Builder(context).create();
   alertDialog.setTitle("No Camera Flash");
      alertDialog.setMessage("The device's camera doesn't support flash.");
      alertDialog.setButton(RESULT_OK, "OK", new DialogInterface.OnClickListener() {
          public void onClick(final DialogInterface dialog, final int which) { 
           Log.e("err", "The device's camera doesn't support flash.");
          }
       });
   alertDialog.show();
  }
 }
 /**
  * @param packageManager
  * @return true <b>if the device support camera flash</b><br/>
  * false <b>if the device doesn't support camera flash</b>
  */
 private boolean isFlashSupported(PackageManager packageManager){ 
  // if device support camera flash?
  if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
   return true;
  } 
  return false;
 }

 /**
  * @param packageManager
  * @return true <b>if the device support camera</b><br/>
  * false <b>if the device doesn't support camera</b>
  */
 private boolean isCameraSupported(PackageManager packageManager){
  // if device support camera?
  if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
   return true;
  } 
  return false;
 }
}