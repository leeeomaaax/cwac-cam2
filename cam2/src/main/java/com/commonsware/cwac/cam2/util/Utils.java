/***
 Copyright (c) 2015 CommonsWare, LLC

 Licensed under the Apache License, Version 2.0 (the "License"); you may
 not use this file except in compliance with the License. You may obtain
 a copy of the License at http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package com.commonsware.cwac.cam2.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Home of static utility methods used by the library and
 * offered to developers in the spirit of togetherness.
 */
public class Utils {
  /**
   * Tests the app and the device to confirm that the code
   * in this library should work. This is called automatically
   * by other classes (e.g., CameraActivity), and so you probably
   * do not need to call it yourself. But, hey, it's a public
   * method, so call it if you feel like it.
   *
   * The method will throw an IllegalStateException if the
   * environment is unsatisfactory, where the exception message
   * will tell you what is wrong.
   *
   * @param ctxt any Context will do
   */
  public static void validateEnvironment(Context ctxt) {
    if (Build.VERSION.SDK_INT<Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
      throw new IllegalStateException("App is running on device older than API Level 14");
    }

    PackageManager pm=ctxt.getPackageManager();

    if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
      throw new IllegalStateException("App is running on device that lacks a camera");
    }

    if (pm.checkPermission(Manifest.permission.CAMERA, ctxt.getPackageName())==PackageManager.PERMISSION_DENIED) {
      throw new IllegalStateException("App lacks the CAMERA permission");
    }
  }
}
