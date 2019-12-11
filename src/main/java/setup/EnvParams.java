package setup;

import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.util.Properties;

public class EnvParams {

    private String adminUsername;
    private String adminPassword;
    private String creatorUsername;
    private String creatorPassword;
    private String editorUsername;
    private String editorPassword;
    private String readOnlyUsername;
    private String readOnlyPassword;
    private String chromeDriver;

    public EnvParams() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("params.properties"));
            adminUsername = props.getProperty("adminUsername");
            adminPassword = props.getProperty("adminPassword");
            creatorUsername = props.getProperty("creatorUsername");
            creatorPassword = props.getProperty("creatorPassword");
            editorUsername = props.getProperty("editorUsername");
            editorPassword = props.getProperty("editorPassword");
            readOnlyUsername = props.getProperty("readOnlyUsername");
            readOnlyPassword = props.getProperty("readOnlyPassword");
            chromeDriver = props.getProperty("chromeDriver");
        }
        catch (IOException e){
            adminUsername = System.getProperty("adminUsername");
            adminPassword = System.getProperty("adminPassword");
            creatorUsername = System.getProperty("creatorUsername");
            creatorPassword = System.getProperty("creatorPassword");
            editorUsername = System.getProperty("editorUsername");
            editorPassword = System.getProperty("editorPassword");
            readOnlyUsername = System.getProperty("readOnlyUsername");
            readOnlyPassword = System.getProperty("readOnlyPassword");
            chromeDriver = System.getProperty("chromeDriver");
        }
    }

    public String getAdminUsername() {
        return adminUsername;
    }
    public String getAdminPassword() {
        return adminPassword;
    }
    public String getCreatorUsername() {
        return creatorUsername;
    }
    public String getCreatorPassword() {
        return creatorPassword;
    }
    public String getEditorUsername() {
        return editorUsername;
    }
    public String getEditorPassword() {
        return editorPassword;
    }
    public String getReadOnlyUsername() {
        return readOnlyUsername;
    }
    public String getReadOnlyPassword() {
        return readOnlyPassword;
    }
    public String getChromeDriver() {return chromeDriver;}
}
