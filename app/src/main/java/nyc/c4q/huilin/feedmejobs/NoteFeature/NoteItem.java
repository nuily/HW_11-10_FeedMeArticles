package nyc.c4q.huilin.feedmejobs.NoteFeature;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by huilin on 11/9/16.
 */

public class NoteItem {
    private String key;
    private String text;

    public NoteItem() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static NoteItem getNew() {
        Locale locale = new Locale("en_US");
        Locale.setDefault(locale);

        String pattern = "MM-dd-yyyy 'at' H:mm a";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String key = formatter.format(new Date());

        NoteItem note = new NoteItem();
        note.setKey(key);
        note.setText("This is where you can add your note");
        return note;

    }

    @Override
    public String toString() {
        return this.getText();
    }
}
