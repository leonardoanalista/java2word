package word.w2004.elements;

/**
 *
 * @author Leonardo
 *
 */
public enum ImageLocation {
    /**
     * FULL_LOCAL_PATH:  Full path absolute (from the root of your server.) including file name and extension.
     * It has to start from the root of your system.
     */
    FULL_LOCAL_PATH,

    /***
     * WEB_URL: It can be http://localhost/your_app/img/xxx.gif or http://google.com/img/logoWhatever.png
     * To know if it will work, you should be able to see this image in your browser
     */
    WEB_URL,

    /**
     * Application classpath
     */
    CLASSPATH;
}
