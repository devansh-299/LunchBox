package com.tip.lunchbox.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.tip.lunchbox.R;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionHelper {

    private static final String TAG = "PermissionHelper";

    public static boolean checkPermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(context, permission)
                    == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    public static void requestPermission(Context context, String permission, int permissionCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                permission)) {
            // Can ask user for permission
            ActivityCompat.requestPermissions(
                    (Activity) context,
                    new String[]{permission},
                    permissionCode);
        } else {
            boolean userAskedPermissionBefore =
                    SharedPreferencesUtil.getBooleanPreference(context, permission, false);

            if (userAskedPermissionBefore) {
                // If User was asked permission before and denied
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setTitle(R.string.permission_needed_title);
                alertDialogBuilder.setMessage(R.string.permission_needed_message);
                alertDialogBuilder.setPositiveButton(
                        R.string.open_settings, (dialogInterface, i) -> {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts(
                                    "package", context.getPackageName(), null);
                            intent.setData(uri);
                            ((Activity)context).startActivity(intent);
                        });
                alertDialogBuilder.setNegativeButton(
                        R.string.cancel, (dialogInterface, i) ->
                                Log.d(TAG, "permission cancelled"));
                AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();
            } else {
                // If user is asked permission for first time
                ActivityCompat.requestPermissions((Activity) context, new String[]{permission},
                        permissionCode);
                SharedPreferencesUtil.setBooleanPreference(context, permission, true);
            }
        }
    }
}
