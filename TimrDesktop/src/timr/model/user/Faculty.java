/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timr.model.user;

import java.util.HashMap;
import java.util.Map;

/**
 * A faculty and it's groups. Used by teacher to send messages.
 * @author kiro
 */
public class Faculty {
    public String name;
    public Map<String,String> groups = new HashMap<String,String>();
}
