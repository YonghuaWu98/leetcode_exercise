package hash_table;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 **/
public class AcHashMap {
    @Test
    public void test() {
        String text = "&amp; is an HTML entity but &amp;assador; is not.";
        String s = entityParser(text);
        System.out.println(s);
    }
    public String entityParser(String text) {
        Map<String, String> map = new HashMap<>();
        map.put("&quot;", "\"");
        map.put("&apos;", "\'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put(" &frasl;", "/");
        StringBuffer s1 = new StringBuffer();
        int n = text.length();
        int i = 0;
        while (i < n) {
            char c = text.charAt(i);
            if (c != '&') {
                s1.append(c);
                i++;
                continue;
            }

            int j = i + 1;

            while (j < n && j - i <= 6) {
                String s = text.substring(i, j + 1);
                if (map.containsKey(s)) {
                    s1.append(map.get(s));
                    i = j;
                    break;
                } else {
                    j++;
                }
            }
            i++;
        }
        String res = s1.toString();
        return res;
    }

}
