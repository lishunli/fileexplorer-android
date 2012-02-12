/*
 * Copyright (c) 2010-2011, The MiCode Open Source Community (www.micode.net)
 *
 * This file is part of FileExplorer.
 *
 * FileExplorer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FileExplorer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SwiFTP.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.micode.fileexplorer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 *
 * @author ShunLi
 */
public class FileExplorerPreferenceActivity extends PreferenceActivity {
    private static final String PRIMARY_FOLDER = "pref_key_primary_folder";
    private static final String READ_ROOT = "pref_key_read_root";

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.preferences);
    }

    public static String getPrimaryFolder(Context context) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String primaryFolder = settings.getString(PRIMARY_FOLDER, GlobalConsts.ROOT_PATH);

        if (primaryFolder.isEmpty()) { // setting primary folder = empty("")
            primaryFolder = GlobalConsts.ROOT_PATH;
        }

        return primaryFolder;
    }

    public static boolean isReadRoot(Context context) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);

        boolean isReadRootFromSetting = settings.getBoolean(READ_ROOT, false);
        boolean isReadRootWhenSettingPrimaryFolderWithoutSdCardPrefix = !getPrimaryFolder(context).startsWith(Util.getSdDirectory());

        return isReadRootFromSetting || isReadRootWhenSettingPrimaryFolderWithoutSdCardPrefix;
    }

}
