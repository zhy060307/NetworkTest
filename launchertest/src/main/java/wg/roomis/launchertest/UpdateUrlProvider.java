package wg.roomis.launchertest;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


public class UpdateUrlProvider {

    private static final String CONTENT_URL = "content://wg.roomis.launcher.provider/ApiConfigEntity";
    private Uri uri;
    private ContentResolver resolver;


    public UpdateUrlProvider(Context context) {
        this.resolver = context.getContentResolver();
        this.uri = Uri.parse(CONTENT_URL);
    }

    public String getUpdateUrl() {
        String url = null;
        Cursor cursor = resolver.query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            url = getString(cursor, "roomis_url", "");

        }

        if (cursor != null) {
            cursor.close();
        }

        return url + "/update.xml";
    }

    private String getString(Cursor cursor, String columnName, String defaultValue) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return columnIndex == -1 ? defaultValue : cursor.getString(columnIndex);
    }
}
