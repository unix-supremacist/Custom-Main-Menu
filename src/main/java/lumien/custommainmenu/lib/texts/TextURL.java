package lumien.custommainmenu.lib.texts;

import java.net.MalformedURLException;
import java.net.URL;

import lumien.custommainmenu.handler.LoadStringURL;

public class TextURL implements IText {

    URL url;
    public String string;

    public TextURL(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.string = "";
        new LoadStringURL(this).start();
    }

    @Override
    public String get() {
        String string = this.string;
        synchronized (string) {
            return this.string;
        }
    }

    public URL getURL() {
        return this.url;
    }
}
